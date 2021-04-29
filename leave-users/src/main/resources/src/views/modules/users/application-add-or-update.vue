<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userName">
      <el-input v-model="dataForm.userName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userDepartment">
      <el-input v-model="dataForm.userDepartment" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="the time that table created" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="the time that table created"></el-input>
    </el-form-item>
    <el-form-item label="the time that application finished" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="the time that application finished"></el-input>
    </el-form-item>
    <el-form-item label="" prop="leaveType">
      <el-input v-model="dataForm.leaveType" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="leaveReason">
      <el-input v-model="dataForm.leaveReason" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="startDate">
      <el-input v-model="dataForm.startDate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="endDate">
      <el-input v-model="dataForm.endDate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="date">
      <el-input v-model="dataForm.date" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="state">
      <el-input v-model="dataForm.state" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          userName: '',
          userDepartment: '',
          createTime: '',
          endTime: '',
          leaveType: '',
          leaveReason: '',
          startDate: '',
          endDate: '',
          date: '',
          state: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userDepartment: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: 'the time that table created不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: 'the time that application finished不能为空', trigger: 'blur' }
          ],
          leaveType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          leaveReason: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          startDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          endDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          date: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/users/application/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.application.userId
                this.dataForm.userName = data.application.userName
                this.dataForm.userDepartment = data.application.userDepartment
                this.dataForm.createTime = data.application.createTime
                this.dataForm.endTime = data.application.endTime
                this.dataForm.leaveType = data.application.leaveType
                this.dataForm.leaveReason = data.application.leaveReason
                this.dataForm.startDate = data.application.startDate
                this.dataForm.endDate = data.application.endDate
                this.dataForm.date = data.application.date
                this.dataForm.state = data.application.state
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/users/application/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'userName': this.dataForm.userName,
                'userDepartment': this.dataForm.userDepartment,
                'createTime': this.dataForm.createTime,
                'endTime': this.dataForm.endTime,
                'leaveType': this.dataForm.leaveType,
                'leaveReason': this.dataForm.leaveReason,
                'startDate': this.dataForm.startDate,
                'endDate': this.dataForm.endDate,
                'date': this.dataForm.date,
                'state': this.dataForm.state
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
