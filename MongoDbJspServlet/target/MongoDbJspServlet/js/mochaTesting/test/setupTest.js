const assert = require('chai').assert;
const sayHello = require('../../index').sayHello;

describe('Index Test',function(){
	it('index should return hello', function(){
		let result = sayHello();
		assert.typeOf(result, 'string');
	});
});