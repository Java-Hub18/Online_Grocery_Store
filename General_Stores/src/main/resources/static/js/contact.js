   	$(document).ready(function() {
            $('#loader').hide();
            $("#submit").on("click", function() {
           // alert("clicked");
            $("#submit").prop("disabled", true);
                var name = $("#name").val();
                var email = $("#email").val();
                var subject = $("#subject").val();
                var message = $("#message").val();
                var formData = $("#form").serialize();
                $('#loader').show();
                if (name === "" || email === "" || message === "" || subject === "") {
                	$("#submit").prop("disabled", false);
                    $('#loader').hide();
                    $("#name").css("border-color", "red");
                    $("#subject").css("border-color", "red");
                    $("#email").css("border-color", "red");
                    $("#message").css("border-color", "red");
                    $("#error_name").html("Please fill the required field.");
                    $("#error_email").html("Please fill the required field.");
                    $("#error_subject").html("Please fill the required field.");
                    $("#error_message").html("Please fill the required field.");
                } else {
                    $("#name").css("border-color", "");
                    $("#email").css("border-color", "");
                    $("#phone").css("border-color", "");
                    $("#message").css("border-color", "");
                    $('#error_name').css('opacity', 0);
                    $('#error_email').css('opacity', 0);
                    $('#error_subject').css('opacity', 0);
                    $('#error_message').css('opacity', 0);
                    var data = {}
        			data["name"] = name;
        			data["email"] = email;
        			data["subject"] = subject;
        			data["message"] = message;
        			var json_data = JSON.stringify(data);
            		//alert(json_data);
                    $.ajax({
                    	type : 'POST',
                    	dataType : "json",
                    	url: "/saveContact",
                        headers : {
                            'Accept' : 'application/json',
                            'Content-Type' : 'application/json'
                        },
                        data : JSON.stringify(data), 
                        success: function(data, statusText, xhr) {
                        	//alert("In Success");
                        	//console.log(xhr.status +" "+data.name);
                        	$('#loader').hide(); 
                            $("#form")[0].reset();
                            $("#error").text("");
                            $("#success").text("Thank you " + name +", for contacting us We will revert you soon.");
                            $('#success').delay(5000).fadeOut('slow');
                            $("#submit").prop("disabled", false);
                        },
                        error: function(e) {
                        	//alert("In error");
                        	$('#loader').hide();
                        	//$("#form")[0].reset();
                            $("#error").text(e.responseText);
                            $('#error').delay(10000).fadeOut('slow');
                            $("#submit").prop("disabled", false);
                        }
                    });
                }
            });
        });