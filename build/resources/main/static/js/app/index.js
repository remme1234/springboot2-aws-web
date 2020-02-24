
// 브라우저 스코프는 공용공간으로 쓰이기 때문에 나중에 로딩된 js의 init, save가 먼저 로딩된 js function을 덮어쓰이게 되기 때문에 따로 유효범위를 만들어서 사용
// 이렇게 하면 main 객체 안에서만 function이 유효하기 때문에 다른 js와 겹칠 위험이 사라진다
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            // PostsApiController에 이미 @PutMapping으로 선언했기 때문에 put을 이용해야 한다, 이는 rest규약에 맞게 설정된 것
            // 참고 : REST 에서 CRUD는 CREATE-POST, READ-GET, UPDATE-PUT, DELETE-DELETE
            type: 'PUT',
            // 어느 게시글을 수정할지 URL PATH로 구분하기 위해 ID를 추가
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();