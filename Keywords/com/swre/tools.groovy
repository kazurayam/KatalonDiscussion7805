package com.swre

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver as WDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject

class tools {
	/**
	 * Execute/evaluate xpath
	 * @param String or testObject
	 * @param RESULT_TYPE: [NUMBER_TYPE|STRING_TYPE|BOOLEAN_TYPE]
	 * @return result from xpath
	 */
	@Keyword
	def exexpath(String xpathExpression, String RESULT_TYPE) {
		def resultTypes = [
			'NUMBER_TYPE': 'numberValue',
			'STRING_TYPE': 'stringValue',
			'BOOLEAN_TYPE': 'booleanValue']
		WDriver wd = DriverFactory.getWebDriver()
		try {
			// document.evaluate(xpathExression, contextNode, namespaceResolver, resultType, result)
			String script = 'return (document.evaluate(\'' + 
				xpathExpression + 
				'\', document, null, XPathResult.ANY_TYPE, null)).' +
				resultTypes[RESULT_TYPE] + ';'
			println("script='${script}'")
			return ((JavascriptExecutor) wd).executeScript(script, [xpathExpression])
		} catch (Exception e) {
			println e
			return null
		}
	}
	def exexpath(TestObject to, String RESULT_TYPE) {
		try{
			exexpath(to.findPropertyValue("xpath"), RESULT_TYPE)
		} catch (Exception e){
			println e
			return null
		}
	}
}