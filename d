[33mcommit 85f234b8614c9a1b23ddf1ef6af80f21347f95ad[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: Adam Sever <avacadoadam@gmail.com>
Date:   Tue Nov 27 18:48:57 2018 +0000

    Added FXML for Panel and a custom panel class to perform default Functions shared amoung all panels

[33mcommit 3fdf59eebf9b9a8b78c4c8878fdc31541c48d6e8[m[33m ([m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: Adam Sever <avacadoadam@gmail.com>
Date:   Sat Nov 24 13:07:37 2018 +0000

    "Create A singleton User to allow for details about the user set in log in Create a panel
    interface for all panels whick extends basic CustomerController"

[33mcommit d8f5bca9c96c75a7bb6d8b4fc8c0a624d0e16edd[m
Author: Adam Sever <avacadoadam@gmail.com>
Date:   Wed Nov 21 11:47:38 2018 +0000

    Add A user singelton class and Logout function as well as handling 'Already Logged In'

[33mcommit e4b33abe4539f9966ec7d43ef705129341923955[m
Author: avacadoadam <avacadoadam@gmail.com>
Date:   Tue Nov 20 18:16:29 2018 +0000

    Create README.md

[33mcommit 6c8b1e11286b4cafeafdebcc4c9135289d47c4a0[m
Author: avaca <avacadoadam@gmail.com>
Date:   Tue Nov 20 18:12:46 2018 +0000

    The Structure for the project is set and will allow quick and easy development
    Howver in the response handler more functionality when logging in is needed
    As a json object representing the users info is returned and this will have
    to be placed in a singelton Object also handle if already Logged in Form

[33mcommit 371fcf0e6d3d63ee1ffa665421b3086e021de917[m
Author: Adam Sever <avacadoadam@gmail.com>
Date:   Tue Nov 13 10:09:48 2018 +0000

    Starting on backendStructure
    -API enum is to represent the url extension such as /Login
    -Connect is a base class that call to database
    Form will handle login and register by any type
    Type will represent the type of user
    Callback for IO operations all Controllers will implement this interface.

[33mcommit 9d32bf045d63f16fec0887cfdff0083337d1abfb[m
Author: avaca <avacadoadam@gmail.com>
Date:   Mon Nov 12 21:27:51 2018 +0000

    First Commit
