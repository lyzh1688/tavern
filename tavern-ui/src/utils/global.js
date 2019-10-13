/**
 * 全局常量、方法封装模块
 * 通过原型挂载到Vue属性
 * 通过 this.Global 调用
 */

 // 后台管理系统服务器地址
// export const baseUrl = 'http://139.196.87.48:8001'
// export const baseUrl = 'http://101.132.134.96:8080'
// export const baseUrl = 'http://119.3.19.171:8080'
export const baseUrl = 'http://127.0.0.1:8080'
 // 系统数据备份还原服务器地址
// export const backupBaseUrl = 'http://139.196.87.48:8002'
// export const backupBaseUrl = 'http://localhost:8002'

export default {
    baseUrl,
    // backupBaseUrl
}
