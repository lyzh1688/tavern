/*
 * 接口统一集成模块
 */
import * as login from './moudules/login'
import * as user from './moudules/user'
import * as dept from './moudules/dept'
import * as role from './moudules/role'
import * as menu from './moudules/menu'
import * as dict from './moudules/dict'
import * as log from './moudules/log'
import * as zzs from './moudules/zzs'
import * as accountant from './moudules/accountant'
import * as client from './moudules/client'
import * as bank from './moudules/bank'
import * as invoice from './moudules/invoice'
import * as extrainvoice from './moudules/extrainvoice'
import * as fileManager from './moudules/fileManager'
import * as salary from './moudules/salary'
import * as customer from './moudules/customer'
import * as order from './moudules/order'


// 默认全部导出
export default {
  login,
  user,
  dept,
  role,
  menu,
  dict,
  log,
  zzs,
  accountant,
  client,
  bank,
  invoice,
  extrainvoice,
  fileManager,
  salary,
  customer,
  order
}
