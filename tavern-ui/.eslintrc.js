// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint'
  },
  env: {
    browser: true,
  },
  extends: [
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    'standard'
  ],
  // required to lint *.vue files
  plugins: [
    'vue'
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-multi-spaces':'off',
    'spaced-comment':'off',
    'eqeqeq':'off',
    'no-useless-escape':'off',
    'no-extend-native':'off',
    'indent' : 'off',
    'space-before-blocks' : 'off',
    'space-before-function-paren':'off',
    'comma-spacing' : 'off',       /*  , 后面必须加一个空格*/
    'key-spacing' : 'off',    //强制在关键字前后使用一致的空格
    'keyword-spacing': 'off',   //在关键字之前和之后强制使用间距
    'semi':'off',           //不允许使用分号代替ASI
    'space-infix-ops': 'off',   //需要在中缀运算符周围的间距
    'block-spacing': 'off',     //在打开块之后和关闭块之前禁止或强制执行块内的空格
    'no-tabs':'off',
    'comma-dangle':'off',
    'no-unused-vars': 'off',    //禁止未使用的变量
    'no-mixed-spaces-and-tabs': 'off',
    'no-trailing-spaces': 'off',
    'no-labels' : 'off',
    'semi-spacing':'off',
    'vue/no-parsing-error' : 'off',
    'space-in-parens': 'off',
    'vue/require-valid-default-prop': 'off',
    'vue/no-duplicate-attributes': 'off',
    'arrow-spacing':'off',
    'handle-callback-err':'off',
    'operator-linebreak': 'off',
    'object-property-newline':'off',
    'padded-blocks': 'off',
    'standard/object-curly-even-spacing':'off',
  }
}
