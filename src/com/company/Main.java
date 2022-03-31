package com.company;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver("Василий", 33);
        Car car = new Car("Track", driver);
        Car clonedCar = null;
        Driver clonedDriver = car.getDriver();
        try {
            clonedCar = new Car(car);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("Оригинал: " + car);
        System.out.println("Клон: " + clonedCar);

        clonedDriver.setAge(34);

        System.out.println("Оригинал после изменения: " + car);
        System.out.println("Клон после изменения: " + clonedCar);
    }

    public static void runEqualsAndHashCode(){
        TestObject x = new TestObject(1, "x");
        TestObject y = new TestObject(1, "x");
        TestObject z = new TestObject(1, "x");
        System.out.println("(x==y) = " + (x==y));
        System.out.println("1. Рефлексивность: x.equals(x) = " + x.equals(x));
        System.out.println("2. Симметричность: если x.equals(y) = " + x.equals(y) + ", тогда y.equals(x) = " + y.equals(x));
        System.out.println("3. Транзитивность: если x.equals(y) = " + x.equals(y) + " и y.equals(z) = " + y.equals(z) +
                ", тогда x.equals(z) = " + x.equals(z));
        System.out.println("4. Консистентность: если x.equals(y) = " + x.equals(y) + ", тогда y.equals(x) = " + y.equals(x));
        System.out.println("5. Null-проверка: x.equals(null) = " + x.equals(null));
        System.out.println("Если x.eqauls(y) то x.hashCode() == y.hashCode() = " + (x.hashCode() == y.hashCode()));
        System.out.println("x.hashCode() = " + x.hashCode());
        System.out.println("y.hashCode() = " + y.hashCode());
    }

    public static void runClass(){
        TestObject test = new TestObject(1, "x");
        System.out.println(test.getClass());
        System.out.println(test);
    }

    public static void runFinalize(){
        FinalizeClass finalizeClass = new FinalizeClass();
        finalizeClass = null;
        System.gc();
        System.out.println("Finish!!!");
    }
}

class Driver implements Cloneable{
    private String name;
    private int age;

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return age == driver.age && Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Driver clone() throws CloneNotSupportedException{
        return (Driver) super.clone();
    }
}

class Car implements Cloneable {
    private String name;
    private Driver driver;

    public Car(String name, Driver driver) {
        this.name = name;
        this.driver = driver;
    }

    public Car(Car otherCar) throws CloneNotSupportedException {
        this(otherCar.getName(), otherCar.getDriver().clone());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(driver, car.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, driver);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", driver=" + driver +
                '}';
    }

    @Override
    public Car clone() throws CloneNotSupportedException{
        Car newCar = (Car) super.clone();
        Driver driver = this.getDriver().clone();
        newCar.setDriver(driver);
        return newCar;
    }
}

class TestObject {
    private int intValue;
    private String stringValue;

    public TestObject(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return intValue == that.intValue && stringValue.equals(that.stringValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intValue, stringValue);
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}

class FinalizeClass {

    public void finalize() {
        System.out.println("I am cleared!");
    }
}
