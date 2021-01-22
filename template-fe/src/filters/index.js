// import parseTime, formatTime and set to filter
export { formatTime } from '@/utils'
import moment from 'moment'

/**
 * Show plural label if time is plural number
 * @param {number} time
 * @param {string} label
 * @return {string}
 */
function pluralize(time, label) {
  if (time === 1) {
    return time + label
  }
  return time + label + 's'
}

/**
 * @param {number} time
 */
export function timeAgo(time) {
  const between = Date.now() / 1000 - Number(time)
  if (between < 3600) {
    return pluralize(~~(between / 60), ' minute')
  } else if (between < 86400) {
    return pluralize(~~(between / 3600), ' hour')
  } else {
    return pluralize(~~(between / 86400), ' day')
  }
}

/**
 * Number formatting
 * like 10000 => 10k
 * @param {number} num
 * @param {number} digits
 */
export function numberFormatter(num, digits) {
  const si = [
    { value: 1E18, symbol: 'E' },
    { value: 1E15, symbol: 'P' },
    { value: 1E12, symbol: 'T' },
    { value: 1E9, symbol: 'G' },
    { value: 1E6, symbol: 'M' },
    { value: 1E3, symbol: 'k' }
  ]
  for (let i = 0; i < si.length; i++) {
    if (num >= si[i].value) {
      return (num / si[i].value).toFixed(digits).replace(/\.0+$|(\.[0-9]*[1-9])0+$/, '$1') + si[i].symbol
    }
  }
  return num.toString()
}

/**
 * 10000 => "10,000"
 * @param {number} num
 */
export function toThousandFilter(num) {
  return (+num || 0).toString().replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','))
}

/**
 * Upper case first char
 * @param {String} string
 */
export function uppercaseFirst(string) {
  return string.charAt(0).toUpperCase() + string.slice(1)
}

/** **
 * 字典 filter，返回value对应的name,value为数字
 * @param value
 * @param dict
 */
export function dictFilter(value, dict, nullValue) {
  nullValue = nullValue || ''
  if (!isNaN(value)) {
    value = parseInt(value)
  }
  if (dict instanceof Array) {
    for (let i = 0; i < dict.length; i++) {
      if (dict[i].value === value) {
        return dict[i].text
      }
    }
  }
  if (dict instanceof Object) {
    for (const item in dict) {
      if (dict[item].value === value) {
        return dict[item].text
      }
    }
  }
  return nullValue
}

export function parseDate(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  if (!time) {
    return null
  }
  var momentObj = moment(time)
  cFormat = cFormat || 'YYYY-MM-DD'
  return momentObj.format(cFormat)
}

export function parseDateTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  if (!time) {
    return null
  }
  var momentObj = moment(time)
  cFormat = cFormat || 'YYYY-MM-DD HH:mm:ss'
  return momentObj.format(cFormat)
}

export function parseTime(time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  if (!time) {
    return null
  }
  var momentObj = moment(time)
  cFormat = cFormat || 'HH:mm:ss'
  return momentObj.format(cFormat)
}
