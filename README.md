#BDD Example

Here I'm using  `cucumber` with `kotlin` to exemplify BDD over a simple sorting method.

##Define examples

We need define examples of the feature behaviour in order to have an easy to understand definition which can be used also as documentation.

Using cucumber we can use Gherkin syntax which is a DSL to define the features we want to test, for example.
```Gherkin
Feature: Sort list
  I want to sort a list of integers

  Scenario: Large list
    Given a list with [2,34,5,6,7,643,3,53,23234,6,241,0,32]
    When I sort the list
    Then the list must be [0,2,3,5,6,6,7,32,34,53,241,643,23234]
```
##Define tests

Having our examples defined we can write the tests to assure the quality of our code, `cucumber` can help us with the templates that we can use to our code if it detects they are missing

```Java
You can implement missing steps with the snippets below:

@Given("a list with [{double}]")
public void a_list_with(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("I sort the list")
public void i_sort_the_list() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("the list must be [{double}]")
public void the_list_must_be(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
```

And having those templates we can implement our real code to test the feature we need (even a helper function to parse a list of integers for example)

```Kotlin
class SortListStepDefinitions {

    private lateinit var list: List<Int>

    @ParameterType("\\[([0-9,]+[0-9]*)*\\]")
    fun intList(rawList: String?) = rawList?.split(',')?.map { it.toInt() } ?: emptyList()

    @When("I sort the list")
    fun i_sort_the_list() {
        list = Sorter.sort(list)
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
```

##Add more examples
Now we can add more examples to describe the expected behaviour of the feature we want to describe and run the tests

```
Scenario: Empty list       # src/test/resources/cucumbertest/sort_list.feature:4
  Given a list with []     # void cucumbertest.SortListStepDefinitions.a_list_with(java.util.List<java.lang.Integer>)
  When I sort the list     # void cucumbertest.SortListStepDefinitions.i_sort_the_list()
  Then the list must be [] # void cucumbertest.SortListStepDefinitions.the_list_must_be(java.util.List<java.lang.Integer>)

Scenario: Singleton list    # src/test/resources/cucumbertest/sort_list.feature:9
  Given a list with [1]     # void cucumbertest.SortListStepDefinitions.a_list_with(java.util.List<java.lang.Integer>)
  When I sort the list      # void cucumbertest.SortListStepDefinitions.i_sort_the_list()
  Then the list must be [1] # void cucumbertest.SortListStepDefinitions.the_list_must_be(java.util.List<java.lang.Integer>)

Scenario: Two elements list   # src/test/resources/cucumbertest/sort_list.feature:14
  Given a list with [3,1]     # void cucumbertest.SortListStepDefinitions.a_list_with(java.util.List<java.lang.Integer>)
  When I sort the list        # void cucumbertest.SortListStepDefinitions.i_sort_the_list()
  Then the list must be [1,3] # void cucumbertest.SortListStepDefinitions.the_list_must_be(java.util.List<java.lang.Integer>)

Scenario: Large list                                           # src/test/resources/cucumbertest/sort_list.feature:19
  Given a list with [2,34,5,6,7,643,3,53,23234,6,241,0,32]     # void cucumbertest.SortListStepDefinitions.a_list_with(java.util.List<java.lang.Integer>)
  When I sort the list                                         # void cucumbertest.SortListStepDefinitions.i_sort_the_list()
  Then the list must be [0,2,3,5,6,6,7,32,34,53,241,643,23234] # void cucumbertest.SortListStepDefinitions.the_list_must_be(java.util.List<java.lang.Integer>)
```