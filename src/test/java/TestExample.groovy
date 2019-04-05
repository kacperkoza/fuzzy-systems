import spock.lang.Specification


class TestExample extends Specification {

    def 'expect 5'() {
        expect:
        Test.number() == 5
    }

}
