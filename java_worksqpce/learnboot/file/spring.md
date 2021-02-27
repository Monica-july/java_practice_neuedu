#spring 官网翻译
本章介绍了Spring框架中控制反转(IoC)原理的实现。IoC也称为依赖注入(DI)。在这个过程中，对象仅通过构造函数参数、工厂方法的参数，或者在对象实例被构造或从工厂方法返回后设置的属性来定义它们的依赖项(即与它们一起工作的其他对象)。然后，容器在创建bean时注入这些依赖项。这个过程从根本上说是bean本身的逆过程(因此称为控制反转)，它通过使用类的直接构造或服务定位器模式这样的机制来控制依赖项的实例化或位置。



org.springframework。豆类和org.springframework。上下文包是Spring框架IoC容器的基础。BeanFactory接口提供了能够管理任何类型对象的高级配置机制。ApplicationContext是BeanFactory的子接口。它补充道:



更容易与Spring的AOP特性集成



消息资源处理(用于国际化)



事件发布



特定于应用程序层的上下文，比如web应用程序中使用的WebApplicationContext。



简而言之，BeanFactory提供了配置框架和基本功能，而ApplicationContext添加了更多特定于企业的功能。ApplicationContext是BeanFactory的一个完整超集，在本章描述Spring的IoC容器时只使用它。有关使用BeanFactory而不是ApplicationContext的更多信息，请参见BeanFactory。



在Spring中，构成应用程序主干并由Spring IoC容器管理的对象称为bean。bean是由Spring IoC容器实例化、组装和管理的对象。否则，bean只是应用程序中众多对象中的一个。bean及其之间的依赖关系反映在容器使用的配置元数据中。

org.springframework.context。ApplicationContext接口表示Spring IoC容器，负责实例化、配置和组装bean。容器通过读取配置元数据获取关于要实例化、配置和组装哪些对象的指令。配置元数据用XML、Java注释或Java代码表示。它允许您表达组成应用程序的对象以及这些对象之间丰富的相互依赖关系。



Spring提供了ApplicationContext接口的几个实现。在独立应用程序中，通常创建ClassPathXmlApplicationContext或FileSystemXmlApplicationContext的实例。虽然XML一直是定义配置元数据的传统格式，但您可以通过提供少量XML配置来声明性地支持这些额外的元数据格式，从而指示容器使用Java注释或代码作为元数据格式。



在大多数应用程序场景中，不需要显式用户代码来实例化Spring IoC容器的一个或多个实例。例如，在web应用程序场景中，应用程序的web. XML文件中简单的8行(大约8行)样板web描述符XML通常就足够了(请参阅方便的ApplicationContext实例化web应用程序)。如果您为Eclipse (Eclipse驱动的开发环境)使用Spring工具，那么只需单击几下鼠标或按键，就可以轻松地创建这个样板配置。



下图展示了Spring工作方式的高级视图。您的应用程序类与配置元数据相结合，这样，在创建并初始化ApplicationContext之后，您就拥有了一个完全配置的可执行系统或应用程序。

![container-magic](./image/container-magic.png)

Figure 1. The Spring IoC container


1.2.1。配置元数据

如上图所示，Spring IoC容器使用一种形式的配置元数据。此配置元数据表示作为应用程序开发人员，您如何告诉Spring容器实例化、配置和组装应用程序中的对象。



配置元数据通常以简单直观的XML格式提供，本章的大部分内容都是用XML格式来传达Spring IoC容器的关键概念和特性。



基于xml的元数据并不是唯一允许的配置元数据形式。Spring IoC容器本身完全与配置元数据实际编写的格式分离。现在，许多开发人员为他们的Spring应用程序选择基于java的配置。

有关在Spring容器中使用其他形式元数据的信息，请参见:



基于注释的配置:Spring 2.5引入了对基于注释的配置元数据的支持。



基于java的配置:从Spring 3.0开始，Spring JavaConfig项目提供的许多特性成为了核心Spring框架的一部分。因此，您可以通过使用Java而不是XML文件来定义应用程序类外部的bean。要使用这些新特性，请参阅@Configuration、@Bean、@Import和@DependsOn注释。



Spring配置由容器必须管理的至少一个(通常不止一个)bean定义组成。基于xml的配置元数据将这些bean配置为顶级元素中的元素。Java配置通常在@Configuration类中使用@ bean注释的方法。



这些bean定义对应于组成应用程序的实际对象。通常，您定义服务层对象、数据访问对象(dao)、表示对象(如Struts动作实例)、基础设施对象(如Hibernate sessionfactory)、JMS队列，等等。通常，不需要在容器中配置细粒度的域对象，因为创建和加载域对象通常是dao和业务逻辑的职责。但是，您可以使用Spring与AspectJ的集成来配置在IoC容器控制之外创建的对象。请参阅使用AspectJ与Spring使用依赖注入域对象。



以下示例显示了基于xml的配置元数据的基本结构:
```aidl
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```
id属性是标识单个bean定义的字符串。

class属性定义bean的类型，并使用完全限定的属性类名。

id属性的值引用协作对象。这个示例中没有显示引用协作对象的XML。有关更多信息，请参见依赖项。
