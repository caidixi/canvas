$(function () {
    const myCanvas = document.getElementById('myCanvas');
    const oc = myCanvas.getContext('2d');
    var groupOfX;
    var groupOfY;
    let endMark = -2;

    initialize();

    myCanvas.onmousedown = function (e) {
        let coordinate = windowToCanvas(e.clientX, e.clientY, myCanvas);
        let {x, y} = coordinate;
        oc.moveTo(x, y);
        groupOfX.push(x);
        groupOfY.push(y);

        document.onmousemove = function (e) {
            let newCoordinate = windowToCanvas(e.clientX, e.clientY, myCanvas);
            let {x, y} = newCoordinate;
            groupOfX.push(x);
            groupOfY.push(y);
            oc.lineTo(x, y);
            oc.stroke();
            oc.strokeStyle = '#000000';
        };
        document.onmouseup = function () {
            groupOfX.push(endMark);
            groupOfY.push(endMark);
            document.onmousemove = document.onmouseup = null;
        }
    };

    function windowToCanvas(x, y, canvas) {
        return {
            x: x - canvas.offsetLeft,
            y: y - canvas.offsetTop
        }
    }

    function getData(){
        let data = {};
        data.name = getName();
        data.endMark = endMark;
        data.x = groupOfX;
        data.y = groupOfY;
        return data;
    }

    function getName(){
        return $("input#name").val();
    }

    function initialize(){
        oc.fillStyle="#FFFFFF";
        oc.beginPath();
        oc.fillRect(0,0,myCanvas.width,myCanvas.height);
        oc.closePath();
        groupOfX = [];
        groupOfY = [];
    }

    function load(data){
        initialize();
        groupOfX = data.x;
        groupOfY = data.y;
        oc.moveTo(groupOfX[0], groupOfY[0]);
        for(let i = 1; i < groupOfX.length; i++){
            if(groupOfX[i] === endMark){
                i = i+1;
                oc.moveTo(groupOfX[i], groupOfY[i]);
            }else {
                oc.lineTo(groupOfX[i], groupOfY[i]);
                oc.stroke();
            }
        }
    }

    $(document).ready(function () {
        $("button#save").click(function () {
            if(getName() === ""){
                alert('请输入名字');
            }else {
                $.ajax({
                    type: 'POST',
                    url: "/mark/setMark/",
                    data: JSON.stringify(getData()),
                    success: function (data) {
                        if(data.state === 0){
                            alert('提交成功，形状为：'+data.shape);
                        }
                    },
                    contentType: 'application/json',
                    dataType: 'json'
                });
            }
        });

        $("input#load").click(function () {
            $.ajax({
                type: 'GET',
                url: "/mark/getMark/"+ getName(),
                success: function (data) {
                    load(data);
                    alert("加载成功")
                },
                contentType: 'application/json',
                dataType: 'json'
            });
        });

        $("button#clear").click(function () {
            initialize();
        });


    });
});