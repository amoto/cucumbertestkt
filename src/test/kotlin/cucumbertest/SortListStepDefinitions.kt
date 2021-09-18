package cucumbertest

import io.cucumber.java.ParameterType
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import junit.framework.Assert.assertEquals

class SortListStepDefinitions {

    lateinit var list: List<Int>

    @ParameterType("\\[([0-9,]+[0-9]*)*\\]")
    fun intList(rawList: String?) = rawList?.split(',')?.map { it.toInt() } ?: emptyList()

    @When("I sort the list")
    fun i_sort_the_list() {
        list = list.sorted()
    }

    @Given("a list with {intList}")
    fun a_list_with(int1: List<Int>) {
        list = int1
    }

    @Then("the list must be {intList}")
    fun the_list_must_be(int1: List<Int>) {
        assertEquals(int1, list)
    }
}