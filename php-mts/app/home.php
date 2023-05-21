<section class="py-3">
    <div class="container">
        <div class="row">
            <div class="col-md-4 position-relative bg-gradient bg-info bg-opacity-25 border-end border-dark">
                <div class="p-3 text-center">
                    <?php 
                    $medicines = $conn->query("SELECT * FROM `medicine_list` where user_id = '{$_settings->userdata('id')}'")->num_rows;
                    ?>
                    <h1 class="text-light"><span id="state1" countto="70"><?= number_format($medicines) ?></span></h1>
                    <h5 class="mt-3 text-light">Medicines</h5>
                    <p class="text-lg h2 font-weight-normal text-light"><span style="font-size:3rem" class="material-icons">medication</span></p>
                </div>
            </div>
            <div class="col-md-4 position-relative bg-gradient bg-info bg-opacity-50 border-end border-dark">
                <div class="p-3 text-center">
                    <?php 
                    $schedule = $conn->query("SELECT * FROM `schedule_list` where user_id = '{$_settings->userdata('id')}' ")->num_rows;
                    ?>
                    <h1 class="text-light"><span id="state1" countto="70"><?= number_format($schedule) ?></span></h1>
                    <h5 class="mt-3 text-light">Schedules</h5>
                    <p class="text-lg h2 font-weight-normal text-light"><span style="font-size:3rem" class="material-icons">calendar_month</span></p>
                </div>
                <hr class="vertical dark">
            </div>
            <!--ZAFER ULGUR Started -->
            <div class="col-md-4 position-relative bg-gradient bg-info bg-opacity-50">
                <div class="p-3 text-center">
                    <?php 
                    $today = date("Y-m-d");
                    $appschedule = $conn->query("SELECT * FROM `appointment_schedule_list` where user_id = '{$_settings->userdata('id')}' and date_format(`date`, '%Y-%m-%d') >= '{$today}'")->num_rows;
                    ?>
                    <h1 class="text-light"><span id="state1" countto="70"><?= number_format($appschedule) ?></span></h1>
                    <h5 class="mt-3 text-light">Active Hospital Appointment Schedules</h5>
                    <p class="text-lg h2 font-weight-normal text-light"><span style="font-size:3rem" class="fa fa-stethoscope">hospital</span></p>
                </div>
                <hr class="vertical dark">
            </div>
            <!--ZAFER ULGUR Finished -->
        </div>
    </div>
</section>

<section class="py-1">
    <div class="container">
        <h1 class="text-center fw-bolder">Welcome to <?= $_settings->info('name') ?></h1>
        <hr>
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-12 mx-auto">
                <h3 class="text-center fw-bolder">Medicines You Need to take Today</h3>
                <div class="table-responsive">
                    <table class="table table-bordered table-sm table-hover table-stripped">
                        <colgroup>
                            <col width="30%"></col>
                            <col width="50%"></col>
                            <col width="20%"></col>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="p-1">Name</th>
                                <th class="p-1">Description</th>
                                <th class="p-1">Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php 
                            $dow = date("Y");
                            $now = date("Y-m-d");
                            $med_sched_sql = "SELECT sl.*, ml.name as med_name, ml.description as med_description FROM `schedule_list` sl inner join medicine_list ml on ml.id = sl.medicine_id where sl.user_id = '{$_settings->userdata('id')}' and date_format(`sl`.`date_start`, '%Y-%m-%d') >= '{$now}' and (until IS NULL OR date_format(`sl`.`until`, '%Y-%m-%d') > '{$now}') order by abs(unix_timestamp(CONCAT('{$now} ',`sl`.`time`)))";
                            $med_sched_qry = $conn->query($med_sched_sql);
                            while($row = $med_sched_qry->fetch_assoc()):
                            ?>
                            <tr>
                                <td><?= $row['med_name'] ?></td>
                                <td><p><?= $row['med_description'] ?></p></td>
                                <td><?= date("g:i A", strtotime($now." ".$row['time'])) ?></td>
                            </tr>
                            <?php endwhile; ?>
                            <?php if($med_sched_qry->num_rows <= 0): ?>
                                <tr>
                                    <td colspan="3" class="text-center">You don't have a scheduled medicine today</td>
                                </tr>
                            <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--ZAFER ULGUR Started -->
            <p></p>
            <hr class="dark">
            <p></p>
            <div class="col-lg-8 col-md-8 col-sm-12 mx-auto">
                <h3 class="text-center fw-bolder">Your Hospital Appointments Today</h3>
                <div class="table-responsive">
                    <table class="table table-bordered table-sm table-hover table-stripped">
                        <colgroup>
                            <col width="30%"></col>
                            <col width="50%"></col>
                            <col width="20%"></col>
                        </colgroup>
                        <thead>
                            <tr>
                                <th class="p-1">Hospital</th>
                                <th class="p-1">Department</th>
                                <th class="p-1">Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php 
                            $now2 = date("Y-m-d");
                            $hos_sched_sql = "SELECT sl.*, ml.name as dep_name FROM `appointment_schedule_list` sl inner join `department_list` ml on `ml`.`id` = `sl`.`department_id` where 1=1 and `sl`.`user_id` = '{$_settings->userdata('id')}' and date_format(`sl`.`date`, '%Y-%m-%d') = '{$now2}' order by abs(unix_timestamp(CONCAT('{$now} ',`sl`.`time`)))";
                            $hos_sched_qry = $conn->query($hos_sched_sql);
                            while($row = $hos_sched_qry->fetch_assoc()):
                            ?>
                            <tr>
                                <td><?= $row['hospital'] ?></td>
                                <td><p><?= $row['dep_name'] ?></p></td>
                                <td><?= date("g:i A", strtotime($now2." ".$row['time'])) ?></td>
                            </tr>
                            <?php endwhile; ?>
                            <?php if($hos_sched_qry->num_rows <= 0): ?>
                                <tr>
                                    <td colspan="3" class="text-center">You don't have a scheduled hospital appointment today</td>
                                </tr>
                            <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--ZAFER ULGUR Finished -->
        </div>
    </div>
</section>