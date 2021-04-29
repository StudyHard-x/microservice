<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userDepartment">
      <el-input v-model="dataForm.userDepartment" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userName">
      <el-input v-model="dataForm.userName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userPosition">
      <el-input v-model="dataForm.userPosition" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="the application of ask for leave" prop="userTable">
      <el-input v-model="dataForm.userTable" placeholder="the application of ask for leave"></el-input>
    </el-form-item>
    <el-form-item label="The rest date of leave" prop="userRest">
      <el-input v-model="dataForm.userRest" placeholder="The rest date of leave"></el-input>
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
          userId: 0,
          userDepartment: '',
          userName: '',
          userPosition: '',
          userTable: '',
          userRest: ''
        },
        dataRule: {
          userDepartment: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userPosition: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userTable: [
            { required: true, message: 'the application of ask for leave不能为空', trigger: 'blur' }
          ],
          userRest: [
            { required: true, message: 'The rest date of leave不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/users/user/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userDepartment = data.user.userDepartment
                this.dataForm.userName = data.user.userName
                this.dataForm.userPosition = data.user.userPosition
                this.dataForm.userTable = data.user.userTable
                this.dataForm.userRest = data.user.userRest
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
              url: this.$http.adornUrl(`/users/user/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'userDepartment': this.dataForm.userDepartment,
                'userName': this.dataForm.userName,
                'userPosition': this.dataForm.userPosition,
                'userTable': this.dataForm.userTable,
                'userRest': this.dataForm.userRest
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
