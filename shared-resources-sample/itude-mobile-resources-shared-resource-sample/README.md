# Sample Resources Project

This is a sample project that contains XML resources to be used for Android MOBBL and iOS MOBBL projects.

## Usage
This project uses maven to expose its resources to dependent projects. In order to make the resources available to other projects, you need to install the project into your local maven repository by running the following command in a terminal:

    $ cd itude-mobile-resources-shared-resource-sample
    $ mvn clean install

The resources project should now be installed in your local maven repository and other projects should be able to reference it from their `pom.xml`. Every time you update the resources project, update your repository using `mvn clean install`.

## License
The code in this project is licensed under the Apache Software License 2.0, per the terms of the included LICENSE file.

