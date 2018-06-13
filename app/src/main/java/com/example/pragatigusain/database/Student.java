package com.example.pragatigusain.database;

class Student
{
    String name,college,address;
    long phone;
    int id;
    public Student(int id,String name,String address,String college, long phone)
    {
        this.id=id;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.college=college;
    }

    public Student(String name, String collegeName, String address, long phone)
    {
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.college=collegeName;
    }

    public Student()
    {

    }

    public long getPhone()
    {
        return phone;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCollege()
    {
        return college;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhone(long phone)
    {
        this.phone = phone;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
