<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Yeseva+One" rel="stylesheet">

    <script src="../../js/html5.js"></script>
    <link rel="stylesheet" href="../../css/ie.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<div class="main-bg">
    <!-- Header -->
    <header>
        <div class="inner">
            <h1 class="logo"><a href="/m"></a></h1>
            <a href="/signIn" style="padding:0 10px 0 10px "><i class="far fa-user fa-3x"
                                                                style="float: right; color: #24ace3; opacity: 0.5 ; padding: 0 10px 0 10px"></i></a>
            <a href="/basket" style="padding: 0 10px 0 10px"><i class="fas fa-shopping-basket fa-3x"
                                                                style="float: right; color: #24ace3 ; opacity: 0.5; padding: 0 10px 0 10px"></i></a>
            <nav>
                <ul class="sf-menu">
                    <li><a href="/shop?category=newbuild">Новостройки</a></li>
                    <li><a href="/shop?category=resale">Вторичная недвижимость</a></li>
                    <li><a href="/shop?category=rental">Аренда</a></li>
                    <li><a href="/shop?category=commerc">Коммерческая недвижимость</a></li>
                    <li><a href="/shop?category=cottage">Загородная недвижимость</a></li>
                </ul>
            </nav>
            <div class="clear"></div>
        </div>
    </header>


    <div class="container_24">
        <div class="wrapper">
            <div class="grid_24 content-bg">
                <div class="wrapper">


                    <p style="margin-bottom: 20px;"></p>

                    <div class="container products">
                    <table>
                            <#list products as product>
                                <div class="product-item">
                                    <div class="row" style="margin: 0 0 20px 0;">
                                        <div class="col-5" style="padding: 0 0 0 80px;"><img src="${product.img}"
                                                                                             style="width: 200px; height: 200px; text-align: right">
                                        </div>
                                        <div class="col-7" style="padding:30px">
                                            <h3>${product.title}</h3>
                                            <span class="price1">Стоимость: ${product.price} руб.</span>
                                            <input type="submit" class="submit_feedback" id="${product.id}" value="Добавить">
                                        </div>
                                    </div>
                                </div>
                            </#list>
                    </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

<#--<script src="../js/add.js"></script>-->
<script type="text/javascript" charset="utf-8">
    // function addProduct() {
    //     let id = document.getElementById("id").value;
    //     $.ajax({
    //         type: 'post',
    //         url: '/basket',
    //         data: {
    //             id: id
    //         }
    //     })
    // }

    $(".submit_feedback").click(function () {
        var id = $(this).attr("data-id");

        $.ajax({
            type: 'post',
            url: '/basket',
            data: {
                productId: id
            },
        });
    });
</script>
</body>
</html>
