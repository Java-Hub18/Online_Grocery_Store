   $(document).ready(function() {
    $('#loader').hide();
    $("#submit").on("click", function() {
    	$("#submit").prop("disabled", true);
    	var name = $("#name").val();
        var file = $("#image").val(); 
        var active = $("#active").val();
        var price = $("#price").val();
        var mrp_price = $("#mrp_price").val();
        var description = $("#description").val();
        var form = $("#form").serialize();
    	var data = new FormData($("#form")[0]);
    	data.append('name', name);
    	data.append('active', active);
    	data.append('mrp_price', mrp_price);
    	data.append('price', price);
    	data.append('description', description); 
    	//alert(data)
        $('#loader').show();
        if (name === "" || file === "" || mrp_price ==="" || active === "" || price === "" || description === "") {
        	$("#submit").prop("disabled", false);
            $('#loader').hide();
            $("#name").css("border-color", "red");
            $("#image").css("border-color", "red");
            $("#active").css("border-color", "red");
            $("#mrp_price").css("border-color", "red");
            $("#price").css("border-color", "red");
            $("#description").css("border-color", "red");
            $("#error_name").html("Please fill the required field.");
            $("#error_file").html("Please fill the required field.");
            $("#error_active").html("Please fill the required field.");
            $("#error_mrp_price").html("Please fill the required field.");
            $("#error_price").html("Please fill the required field.");
            $("#error_description").html("Please fill the required field.");
        } else {
            $("#name").css("border-color", "");
            $("#image").css("border-color", "");
            $("#status").css("border-color", "");
            $("#price").css("border-color", "");
            $("#mrp_price").css("border-color", "");
            $("#description").css("border-color", "");
            $('#error_name').css('opacity', 0);
            $('#error_file').css('opacity', 0);
            $('#error_active').css('opacity', 0);
            $('#error_price').css('opacity', 0);
            $('#error_mrp_price').css('opacity', 0);
            $('#error_description').css('opacity', 0);
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/admin/product/saveProduct", 
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        	$('#loader').hide(); 
                        	$("#form")[0].reset();
                        	$("#submit").prop("disabled", false);
                        	$('#success').css('display','block');
                            $("#error").text("");
                            $("#success").text(data);
                            $('#success').delay(3000).fadeOut('slow');
                            //location.reload();
                         }	   
                        },
                        error: function(e) {
                        	$('#loader').hide();
                        	$('#error').css('display','block');
                            $("#error").html("Oops! something went wrong.");
                            $('#error').delay(5000).fadeOut('slow');
                            location.reload();
                        }
                    });
        }
            });
        });