<!--ZAFER ULGUR Started -->
<?php 
if(isset($_GET['id'])){
    $qry = $conn->query("SELECT * FROM `appointment_schedule_list` where id = '{$_GET['id']}' ");
    if($qry->num_rows > 0 ){
        foreach($qry->fetch_array() as $k => $v){
            if(!is_numeric($k))
            $$k = $v;
        }
    }
}
?>
<section class="py-5">
    <div class="container">
        <h2 class="fw-bolder text-center"><b><?= isset($id) ? "Edit Appointment Schedule" : "Add New Appointment Schedule" ?></b></h2>
        <hr>
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12 col-xs-12">
                <form action="" id="schedule-form" class="py-3">
                    <input type="hidden" name="id" value="<?= isset($id) ? $id : '' ?>">
                    <input type="hidden" name="user_id" value="<?=  $_settings->userdata('id') ?>">
                    <div class="input-group mb-4 input-group-static is-filled">
                        <label for="department_id" class="form-label">Department <span class="text-primary">*</span></label>
                        <select id="department_id" name="department_id" value="<?= isset($location) ? $location : "" ?>" class="form-select" required>
                        <?php 
                        $categories = $conn->query("SELECT * FROM `department_list` order by `id` asc");
                        while($row = $categories->fetch_assoc()):
                        ?>
                        <option value="<?= $row['id'] ?>" <?= isset($cats) && in_array($row['id'], $cats) ? "selected" : "" ?>><?= $row['name'] ?></option>
                        <?php endwhile; ?>
                        </select>
                    </div>
                    <div class="input-group mb-4 input-group-dynamic is-filled">
                        <label for="date" class="form-label">Date <span class="text-primary">*</span></label>
                        <input type="date" id="date" name="date" value="<?= isset($date) ? $date : "" ?>" class="form-control" required>
                    </div>
                    <div class="input-group mb-4 input-group-dynamic is-filled">
                        <label for="time" class="form-label">Time <span class="text-primary">*</span></label>
                        <input type="time" id="time" name="time" value="<?= isset($time) ? $time : "" ?>" class="form-control" required>
                    </div>
                    <div class="input-group mb-4 input-group-dynamic is-filled">
                        <label for="hospital" class="form-label">Hospital <span class="text-primary">*</span></label>
                        <textarea rows="1" id="hospital" name="hospital" class="form-control border rounded-0 px-2 py-1" required><?= isset($hospital) ? $hospital : "" ?></textarea>
                    </div>
                    <div class="input-group mb-4 input-group-dynamic is-filled">
                        <label for="remarks" class="form-label">Remarks <span class="text-primary">*</span></label>
                        <textarea rows="2" id="remarks" name="remarks" class="form-control border rounded-0 px-2 py-1" required><?= isset($remarks) ? $remarks : "" ?></textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn bg-primary bg-gradient btn-sm text-light w-25"><span class="material-icons">save</span> Save</button>
                            <a href="./?page=appschedules" class="btn bg-deafult border bg-gradient btn-sm w-25"><span class="material-icons">keyboard_arrow_left</span> Cancel</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script>
    var fuser_ajax;
    $(function(){
        $('#department_id').select2({
            placeholder:"Please Select Here",
            width:"100%",
        })
        $('#schedule-form').submit(function(e){
            e.preventDefault()
            $('.pop-alert').remove()
            var _this = $(this)
            var el = $('<div>')
            el.addClass("pop-alert alert alert-danger text-light")
            el.hide()
            start_loader()
            $.ajax({
                url:'../classes/Master.php?f=save_appschedule',
                method:'POST',
                data:$(this).serialize(),
                dataType:'json',
                error:err=>{
                    console.error(err)
                    el.text("An error occured while saving data z")
                    _this.prepend(el)
                    el.show('slow')
                    $('html, body').scrollTop(_this.offset().top - '150')
                    end_loader()
                },
                success:function(resp){
                    if(resp.status == 'success'){
                        location.href= './?page=appschedules/view_details&id='+resp.sid;
                    }else if(!!resp.msg){
                        el.text(resp.msg)
                        _this.prepend(el)
                        el.show('slow')
                        $('html, body').scrollTop(_this.offset().top - '150')
                    }else{
                        el.text("An error occured while saving data zfr")
                        _this.prepend(el)
                        el.show('slow')
                        $('html, body').scrollTop(_this.offset().top - '150')
                    }
                    end_loader()
                    console

                }
            })
        })

    })
</script>
<!--ZAFER ULGUR Finished -->