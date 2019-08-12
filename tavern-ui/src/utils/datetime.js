/**
 * 时间日期相关操作
 */


/**
 * 时间格式化
 * 将 2018-09-23T11:54:16.000+0000 格式化成 2018/09/23 11:54:16
 * @param datetime 国际化日期格式
 */
export function format(datetime) {
  return formatWithSeperator(datetime, "/", ":", "0");
}

export function formatDate(date) {
  return formatWithSeperator(date, "/", ":", "1");
}

export function formatDateSimple8(date) {
  return formatWithSeperator(date, "/", ":", "2");
}

/**
 * 时间格式化
 * 将 2018-09-23T11:54:16.000+0000 格式化成类似 2018/09/23 11:54:16
 * 可以指定日期和时间分隔符
 * @param datetime 国际化日期格式
 */
export function formatWithSeperator(datetime, dateSeprator, timeSeprator, type) {
  if (datetime != null) {
    const dateMat = new Date(datetime);
    const year = dateMat.getFullYear();
    const month = makeupLength(dateMat.getMonth() + 1);
    const day = makeupLength(dateMat.getDate());
    if (type === "1") {
      return year + dateSeprator + month + dateSeprator + day;
    }
    if (type === "2") {
      return year + month + day;
    }
    const hh = makeupLength(dateMat.getHours());
    const mm = makeupLength(dateMat.getMinutes());
    const ss = makeupLength(dateMat.getSeconds());
    return year + dateSeprator + month + dateSeprator + day + " " + hh + timeSeprator + mm + timeSeprator + ss;
  }
}

function makeupLength(date) {
  if (date <= 9) {
    return '0' + date;
  }
  return date;
}
