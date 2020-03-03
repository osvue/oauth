<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Activity name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Activity zone">
        <el-select v-model="form.region" placeholder="please select your zone">
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>
      </el-form-item>
      <el-form-item label="Activity time">
        <el-col :span="11">
          <el-date-picker v-model="form.date1" value-format="yyyy-MM-dd" type="date" placeholder="Pick a date" style="width: 100%;" />
        </el-col>


      </el-form-item>

      <el-form-item label="Activity type">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="abc" name="type" >Lusifer</el-checkbox>
          <el-checkbox label="def" name="type" />
          <el-checkbox label="ghi" name="type" />
          <el-checkbox label="adc" name="type" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio    label="1"  >男</el-radio>
          <el-radio    label="0"  >女</el-radio >
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: 'Info',
    data(){
      return{
        //表单对象
        form: {
          name: '',
          region: '',
          date1: '',
          type: [],
          sex: '',
        }
      }
    },
    methods:{
      onSubmit(){
        console.log(this.form)
      },
      getData(){
        this.username = this.$store.getters.name
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      }
    },
    created() {
      this.getData()
    }
  }
</script>

<style scoped>

</style>
