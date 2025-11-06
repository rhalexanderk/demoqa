package steps
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.openqa.selenium.Keys
import com.kms.katalon.core.configuration.RunConfiguration



class DemoQA_Step {
	@Given("User mengakses halaman registrasi form DemoQA")
	def bukaHalaman() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://demoqa.com/automation-practice-form')
	}

	@When("User mengisi form dengan data: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	def fillForm(String firstName, String lastName, String dob, String email, String gender, String mobile, String subject, String hobbies, String picture, String address, String state, String city) {

		WebUI.setText(findTestObject('Forms/input_FirstName'), firstName)
		WebUI.setText(findTestObject('Forms/input_LastName'), lastName)
		WebUI.setText(findTestObject('Forms/input_Email'), email)
		WebUI.setText(findTestObject('Forms/input_Number'), mobile)

		if (!dob.trim().isEmpty()) {
			WebUI.click(findTestObject('Object Repository/Forms/input_DOB'))
			WebUI.sendKeys(findTestObject('Object Repository/Forms/input_DOB'), Keys.chord(Keys.CONTROL, "a"))
			WebUI.sendKeys(findTestObject('Object Repository/Forms/input_DOB'), dob)
			WebUI.sendKeys(findTestObject('Object Repository/Forms/input_DOB'), Keys.chord(Keys.ENTER))
		}
		def cleanedGender = gender.trim().toLowerCase()
		if (cleanedGender == 'male') {
			WebUI.click(findTestObject('Forms/radio_Male'))
		} else if (cleanedGender == 'female') {
			WebUI.click(findTestObject('Forms/radio_Female'))
		} else if (cleanedGender == 'other') {
			WebUI.click(findTestObject('Forms/radio_Other'))
		}

		if (!subject.trim().isEmpty()) {
			String[] subjects = subject.split(',').collect{ it.trim() }
			for (String sub : subjects) {
				// Kita harus mengetik, lalu menekan ENTER untuk membuat tag
				WebUI.setText(findTestObject('Forms/input_Subject'), sub)
				WebUI.sendKeys(findTestObject('Forms/input_Subject'), Keys.chord(Keys.ENTER))
				WebUI.delay(1)
			}
		}

		if (!hobbies.trim().isEmpty()) {
			String[] hobbyList = hobbies.split(',').collect{ it.trim() }
			for (String hobby : hobbyList) {
				WebUI.click(findTestObject('Forms/checkbox_Hobby', [('label'): hobby]))
			}
		}

		def pictureFileName = picture.trim()

		if (!pictureFileName.isEmpty()) {
			def projectDir = RunConfiguration.getProjectDir()
			def relativePath = projectDir + "/Data Files/img/" + pictureFileName
			WebUI.uploadFile(findTestObject('Forms/upload_Picture'), relativePath)
		}

		WebUI.setText(findTestObject('Forms/input_CurrentAddress'), address)

		if (!state.trim().isEmpty()) {
			WebUI.click(findTestObject('Forms/ddl_State'))
			WebUI.sendKeys(findTestObject('Forms/input_State'), state)
			WebUI.sendKeys(findTestObject('Forms/input_State'), Keys.chord(Keys.ENTER))

			if (!city.trim().isEmpty()) {
				WebUI.click(findTestObject('Forms/ddl_City'))
				WebUI.sendKeys(findTestObject('Forms/input_city'), city)
				WebUI.sendKeys(findTestObject('Forms/input_city'), Keys.chord(Keys.ENTER))
			}
		}
	}

	@Then("Hasil form submission harus diverifikasi: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	def verifikasiHasil(String tcId, String firstName, String lastName, String dob, String email, String gender, String mobile, String subject, String hobbies, String picture, String address, String state, String city, String expectedResult) {
		WebUI.scrollToElement(findTestObject('Forms/btn_Submit'), 5)
		WebUI.click(findTestObject('Forms/btn_Submit'))

		// Cek apakah modal sukses MUNCUL.
		def isSuccessModalPresent = WebUI.verifyElementPresent(findTestObject('Forms/modal_SubmissionResult'), 10, FailureHandling.OPTIONAL)

		if (expectedResult.trim().toLowerCase() == 'success') {
			WebUI.verifyElementPresent(findTestObject('Forms/modal_SubmissionResult'), 10)

			def expectedStudentName = "${firstName} ${lastName}"
			def expectedDOBText = formatDOBForModal(dob)
			def expectedStateCity = "${state} ${city}" // Gabungkan State dan City

			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Student Name']), expectedStudentName)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Student Email']), email)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Gender']), gender)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Mobile']), mobile)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Date of Birth']), expectedDOBText)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Subjects']), subject)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Hobbies']), hobbies.replace(', ', ',').replace(',', ', ')) // Menyesuaikan format display
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Picture']), picture)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'Address']), address)
			WebUI.verifyElementText(findTestObject('Forms/text_SubmissionValue', [('label'): 'State and City']), expectedStateCity)

			WebUI.click(findTestObject('Forms/btn_CloseModal'))
		} else {
			if (isSuccessModalPresent) {
				KeywordUtil.markFailed("TC_ID: ${tcId} GAGAL KRITIS. Expected: GAGAL submit (${expectedResult}), Actual: Submit BERHASIL (Modal Sukses Muncul).")
				WebUI.click(findTestObject('Forms/btn_CloseModal'), FailureHandling.OPTIONAL)
			} else {
				KeywordUtil.logInfo("TC NEGATIF BERHASIL: TC_ID ${tcId} lulus karena form gagal submit sesuai harapan (${expectedResult}).")
			}
		}
		WebUI.closeBrowser()
	}
	def formatDOBForModal(String dobInput) {
		def parts = dobInput.trim().split(' ')
		def day = parts[0]
		def monthName = parts[1].toUpperCase()
		def year = parts[2]

		def fullMonthNameMap = [
			'JAN': 'January', 'FEB': 'February', 'MAR': 'March', 'APR': 'April',
			'MAY': 'May', 'JUN': 'June', 'JUL': 'July', 'AUG': 'August',
			'SEP': 'September', 'OCT': 'October', 'NOV': 'November', 'DEC': 'December'
		]

		def fullMonth = fullMonthNameMap.get(monthName)

		return "${day} ${fullMonth},${year}"
	}
}