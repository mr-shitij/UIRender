# UIRender

UIRender is java application which take ui description in form of json object and then
convert it to code  
  
*Note : java swing components are used*

### Applications
1. It can be used to create drag and drop ui editors.
   
2. It can be used in a way so that we can construct code from text description or by voice.  

3. It can be used to create a desktop application development framework as like android has its xml to ui we can have json to ui. 

4. Support of new languages can be added as needed.

### Supported Java Swing Component's
- [Button](#button) 
  
- [Label](#label)
  
- [ComboBox](#combobox)

- [Checkbox](#checkbox)
  
- [List](#list)
  
- [Panel](#panel)
  
- [PasswordField](#passwordfield)
  
- [TextArea](#textarea)
  
- [TextField](#textfield)
  
- [RadioButton](#radiobutton)
  
- [RadioGroup](#radiogroup)
  
- [ScrollPane](#scrollpane)
  
- [Slider](#slider)
  
- [Table](#table)
  
- [ProgressBar](#progressbar)
  
- [Menu](#menu-and-its-items)
   * [MenuBar](#menubar)
   * [Menu](#menu)
   * [MenuItem](#menuitem)
   * [CheckboxMenuItem](#checkboxmenuitem)
   * [RadioButtonMenuItem](#radiobuttonmenuitem)
   * [RadioGroupMenuItem](#radiogroupmenuitem)

### Defining Frame
    {
      "lang": String,
      "frame": {
        "id": String,
        "title": String,
        "layout":{
          "name": String,
            .
            .
            .
        },
        "size": {
          "width": Integer,
          "height": Integer
        },
        "show": Boolean,
        "components" : [
            {
                "type": String,
                    .
                    .
                    .
            },
            .
            .
            .
        ]
      }
    }

  Above properties are specified below :     
  - **lang** : name of layout we are using border layout so name is `borderlayout`. 
  - **frame** : the vertical gap of border layout.
    * id : name of file for saving code. 
    * title : title for the frame. 
    * layout : layout for the frame for more info look at [layouts](#layouts). 
  - **size** : It defines the size for the frame.
    * width : width of frame. 
    * height: height of frame. 
  - **frame** : for making the frame visible or invisible.
  - **components** : array of components for more info look at [Component Wise Description](#component-wise-description).

  **Example**  

    {
      "lang":"java",
      "frame": {
        "id": "frameOne",
        "title": "First App",
        "layout":{
          "name":"flowlayout",
        },
        "size": {
          "width": 500,
          "height": 300
        },
        "show": true,
        "components" : [
          {
            "type": "button",
            "id": "button_variable_name",
            "text": "button_name",
            "icon": "button_icon.png",
            "foreground-color": [10, 100, 50],
            "background-color": [50, 100, 20],
            "font": {
              "name":"arial",
              "size": 30,
              "style": "plain"
            },
            "pos": {
                "x": 100,
                "y": 20,
                "width": 100,
                "height": 50 
            },
            "global": true,
            "enable": true
          }
            
        ]
      }
    }

### Layouts

#### BorderLayout
        
        "layout": {
            "name": "borderlayout",
            "hgap": Integer,
            "vgap": Integer
        }

  Above properties are specified below :     
  - **name** : name of layout we are using border layout so name is `borderlayout`. 
  - **hgap** : the vertical gap of border layout. 
  - **vgap** : the horizontal gap of border layout. 

  **Example**  

        "layout": {
            "name": "borderlayout",
            "hgap": 5,
            "vgap": 5
        }


#### FlowLayout
        
        "layout": {
            "name": "flowlayout",
            "align": String,
            "hgap": Integer,
            "vgap": Integer
        }

  Above properties are specified below :     
  - **name** : name of layout we are using flow layout so name is `flowlayout`. 
  - **align** : the alignment for flow layout It can be (left, right, center, leading or trailing). 
  - **hgap** : the vertical gap of flow layout. 
  - **vgap** : the horizontal gap of flow layout. 

  **Example**  

        "layout": {
            "name": "flowlayout",
            "align": "left",
            "hgap": 5,
            "vgap": 5
        }


#### GridLayout
        
        "layout": {
            "name": "gridlayout",
            "rows": Integer,
            "columns": Integer,
            "hgap": Integer,
            "vgap": Integer
        }

  Above properties are specified below :     
  - **name** : name of layout we are using grid layout so name is `gridlayout`. 
  - **rows** : the rows for grid layout. 
  - **columns** : the columns for grid layout. 
  - **hgap** : the vertical gap of grid layout. 
  - **vgap** : the horizontal gap of grid layout. 

  **Example**  

        "layout": {
            "name": "gridlayout",
            "rows": 3,
            "columns": 10,
            "hgap": 5,
            "vgap": 5
        }




### Component Wise Description


#### Button  
    
      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for button component. 
  - **text** : It is used to set the text of button. 
  - **icon** : It is used to set the icon of button. 
  - **foreground-color** : It is used to set the foreground color of button. 
  - **background-color** : It is used to set the background color of button. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of Button Component. 
    - **y** : Y-Coordinate of Button Component. 
    - **width** : X-Coordinate of Button Component. 
    - **height** : X-Coordinate of Button Component. 
  - **layout-align** : It is used to set the position of button component while using border layout. 
  - **global** : It is used to make button variable public. 
  - **enable** : It is used to enable or disable button component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "button",
        "id": "button_variable_name",
        "text": "button_name",
        "icon": "button_icon.png",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "font": {
          "name":"arial",
          "size": 30,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 20,
            "width": 100,
            "height": 50 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "button",
        "id": "button_variable_name",
        "text": "button_name",
        "icon": "button_icon.png",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "bold"
        },
        "layout-align": "north",
        "global": true,
        "enable": true
      }
    

#### Label  
    
      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "align": String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for label component. 
  - **text** : It is used to set the text of label. 
  - **icon** : It is used to set the icon of button. 
  - **foreground-color** : It is used to set the foreground color of label. 
  - **background-color** : It is used to set the background color of label. 
  - **align** : It is used to align the label It can be left, right, center, leading or trailing. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of label Component. 
    - **y** : Y-Coordinate of label Component. 
    - **width** : X-Coordinate of label Component. 
    - **height** : X-Coordinate of label Component. 
  - **layout-align** : It is used to set the position of label component while using border layout. 
  - **global** : It is used to make label variable public. 
  - **enable** : It is used to enable or disable label component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "label",
        "id": "label_variable_name",
        "text": "label_name",
        "icon": "label_icon.png",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "align": "center",
        "font": {
          "name":"arial",
          "size": 30,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 20,
            "width": 100,
            "height": 50 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "label",
        "id": "label_variable_name",
        "text": "label_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "align": "left",
        "font": {
          "name":"arial",
          "size": 10,
          "style": "bold"
        },
        "layout-align": "north",
        "global": true,
        "enable": true
      }
    

#### ComboBox  
    
      {
        "type": String,
        "id": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "item": Array,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for combobox component. 
  - **item** : It is used to add item to the combobox. 
  - **foreground-color** : It is used to set the foreground color of combobox. 
  - **background-color** : It is used to set the background color of combobox. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of combobox Component. 
    - **y** : Y-Coordinate of combobox Component. 
    - **width** : X-Coordinate of combobox Component. 
    - **height** : X-Coordinate of combobox Component. 
  - **layout-align** : It is used to set the position of combobox component while using border layout. 
  - **global** : It is used to make combobox variable public. 
  - **enable** : It is used to enable or disable combobox component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "combobox",
        "id": "combobox_variable_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "item": ["combobox_item1", "combobox_item2"],
        "font": {
          "name":"arial",
          "size": 30,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 20,
            "width": 100,
            "height": 50 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "combobox",
        "id": "combobox_variable_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "item": ["combobox_item1", "combobox_item2"],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "bold"
        },
        "layout-align": "north",
        "global": true,
        "enable": true
      }
    

#### Checkbox  
    
      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for Checkbox component. 
  - **text** : It is used to set the text of Checkbox. 
  - **foreground-color** : It is used to set the foreground color of Checkbox. 
  - **background-color** : It is used to set the background color of Checkbox. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of Checkbox Component. 
    - **y** : Y-Coordinate of Checkbox Component. 
    - **width** : X-Coordinate of Checkbox Component. 
    - **height** : X-Coordinate of Checkbox Component. 
  - **layout-align** : It is used to set the position of Checkbox component while using border layout. 
  - **global** : It is used to make Checkbox variable public. 
  - **enable** : It is used to enable or disable Checkbox component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "checkbox",
        "id": "checkbox_variable_name",
        "text": "checkbox_name",
        "icon": "checkbox_icon.png",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "font": {
          "name":"arial",
          "size": 30,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 20,
            "width": 100,
            "height": 50 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "checkbox",
        "id": "checkbox_variable_name",
        "text": "checkbox_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "bold"
        },
        "layout-align": "north",
        "global": true,
        "enable": true
      }
    

#### List  
    
      {
        "type": String,
        "id": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "data": Array/Sting,
        "selection-foreground-color": Array/String,
        "selection-background-color": Array/String,
        "width": Integer,
        "height": Integer, 
        "scroll-pane": Object/Boolean, 
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for List component. 
  - **foreground-color** : It is used to set the foreground color of List. 
  - **background-color** : It is used to set the background color of List. 
  - **data** : It is used to add an element to the List. 
  - **selection-foreground-color** : It is used to set the foreground color when an element is selected from List. 
  - **selection-background-color** : It is used to set the background color when an element is selected from List. 
  - **width** : It is used to set width of cell.
  - **height** : It is used to set height of cell.
  - **scroll-pane** : It is used to make List scrollable _(Note: Refer to [ScrollPane](#scrollpane)_.
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of List Component. 
    - **y** : Y-Coordinate of List Component. 
    - **width** : X-Coordinate of List Component. 
    - **height** : X-Coordinate of List Component. 
  - **layout-align** : It is used to set the position of List component while using border layout. 
  - **global** : It is used to make List variable public. 
  - **enable** : It is used to enable or disable List component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "list",
        "id": "list_variable_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "data": ["List_item1", "List_item2"],
        "selection-foreground-color": [0, 200, 0],
        "selection-background-color": [0, 200, 255],
        "width": 100,
        "height": 100,
        "scroll-pane": true, 
        "font": {
          "name":"arial",
          "size": 30,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 20,
            "width": 100,
            "height": 50 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "list",
        "id": "list_variable_name",
        "foreground-color": [10, 100, 50],
        "background-color": [50, 100, 20],
        "data": ["List_item1", "List_item2"],
        "selection-foreground-color": [0, 200, 0],
        "selection-background-color": [0, 200, 255],
        "width": 100,
        "height": 100,
        "font": {
          "name":"arial",
          "size": 10,
          "style": "bold"
        },
        "layout-align": "north",
        "global": true,
        "enable": true
      }

#### Panel  
    
      {
        "type": String,
        "id": String,
        "background-color": Array/String,
        "scroll-pane": Object/Boolean, 
        "layout": {
            "name": String,
                .
                .
                .
        },
        "components": Array,
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for panel component. 
  - **background-color** : It is used to set the background color of panel. 
  - **scroll-pane** : It is used to make List scrollable _(Note: Refer to [ScrollPane](#scrollpane)_.
  - **layout** : It is used to set the layout of panel (Refer to this for more details about [layouts](#layouts)). 
  - **components** : It is used to add components in panel It takes an array of components. 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of panel. 
    - **y** : Y-Coordinate of panel. 
    - **width** : X-Coordinate of panel. 
    - **height** : X-Coordinate of panel. 
  - **layout-align** : It is used to set the position of panel component while using border layout in parent. 
  - **global** : It is used to make panel variable public. 
  - **enable** : It is used to enable or disable panel component. 
        
       
  Example  
    1. Using position. 
  
      {
        "type": "panel",
        "id": "panel_ui",
        "background-color": "red",
        "scroll-pane": {
        	"vertical":true,
        	"horizontal":false
        },
        "layout": {
            "name": "borderlayout",
            "hgap": 5,
            "vgap": 5
        }
        "components": [
          {
            "type": "button",
            "id": "button_variable_name",
            "text": "button_name",
            "icon": "button_icon.png",
            "foreground-color": [10, 100, 50],
            "background-color": [50, 100, 20],
            "font": {
              "name":"arial",
              "size": 10,
              "style": "bold"
            },
            "layout-align": "north",
            "global": true,
            "enable": true
          }
        ],
        "pos": {
            "x": 10,
            "y": 10,
            "width": 500,
            "height": 300 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  
  
      {
        "type": "panel",
        "id": "panel_ui",
        "background-color": "red",
        "scroll-pane": true, 
        "layout": {
            "name": "borderlayout",
            "hgap": 5,
            "vgap": 5
        }
        "components": [
          {
            "type": "button",
            "id": "button_variable_name",
            "text": "button_name",
            "icon": "button_icon.png",
            "foreground-color": [10, 100, 50],
            "background-color": [50, 100, 20],
            "font": {
              "name":"arial",
              "size": 10,
              "style": "bold"
            },
            "layout-align": "north",
            "global": true,
            "enable": true
          }
        ],
        "layout-align": "center",
        "global": true,
        "enable": true
      }

#### PasswordField
    
      {
        "type": String,
        "id": String,
        "text": String,
        "columns": Integer,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "align": String,
        "scroll-pane": Object/Boolean,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for passwordField component. 
  - **text** : It is used to set the text of passwordField. 
  - **columns** : It is used to set the number of columns of passwordField. 
  - **foreground-color** : It is used to set the foreground color of passwordField. 
  - **background-color** : It is used to set the background color of passwordField. 
  - **align** : It is used to align the passwordField It can be left, right, center, leading or trailing. 
  - **scroll-pane** : It is used to make passwordField scrollable _(Note:No need to specify specific value; Once you added it to object passwordField become scrollable)_.
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of passwordField Component. 
    - **y** : Y-Coordinate of passwordField Component. 
    - **width** : X-Coordinate of passwordField Component. 
    - **height** : X-Coordinate of passwordField Component. 
  - **layout-align** : It is used to set the position of passwordField component while using border layout. 
  - **global** : It is used to make passwordField variable public. 
  - **enable** : It is used to enable or disable passwordField component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "passwordfield",
        "id": "password",
        "text": "enter password",
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "pos": {
            "x": 10,
            "y": 10,
            "width": 30,
            "height": 10 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  

      {
        "type": "passwordfield",
        "id": "password",
        "text": "enter password",
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "layout-align": "south",
        "global": true,
        "enable": true
      }



#### TextArea
    
      {
        "type": String,
        "id": String,
        "text": String,
        "rows": Integer,
        "columns": Integer,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "align": String,
        "scroll-pane": Object/Boolean,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for textArea component. 
  - **text** : It is used to set the text of textArea. 
  - **rows** : It is used to set the number of rows of textArea. 
  - **columns** : It is used to set the number of columns of textArea. 
  - **foreground-color** : It is used to set the foreground color of textArea. 
  - **background-color** : It is used to set the background color of textArea. 
  - **align** : It is used to align the textArea It can be left, right, center, leading or trailing. 
  - **scroll-pane** : It is used to make textArea scrollable _(Note:No need to specify specific value; Once you added it to object textarea become scrollable)_.
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of textArea Component. 
    - **y** : Y-Coordinate of textArea Component. 
    - **width** : X-Coordinate of textArea Component. 
    - **height** : X-Coordinate of textArea Component. 
  - **layout-align** : It is used to set the position of textArea component while using border layout. 
  - **global** : It is used to make textArea variable public. 
  - **enable** : It is used to enable or disable textArea component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "textarea",
        "id": "textarea",
        "text": "enter message",
        "rows": 5,
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "pos": {
            "x": 10,
            "y": 10,
            "width": 30,
            "height": 10 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  

      {
        "type": "textarea",
        "id": "area",
        "text": "enter message",
        "rows": 5,
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "layout-align": "south",
        "global": true,
        "enable": true
      }

#### TextField

    
      {
        "type": String,
        "id": String,
        "text": String,
        "columns": Integer,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "align": String,
        "scroll-pane": Object/Boolean,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for TextField component. 
  - **text** : It is used to set the text of TextField. 
  - **columns** : It is used to set the number of columns of TextField. 
  - **foreground-color** : It is used to set the foreground color of TextField. 
  - **background-color** : It is used to set the background color of TextField. 
  - **align** : It is used to align the TextField It can be left, right, center, leading or trailing. 
  - **scroll-pane** : It is used to make TextField scrollable _(Note:No need to specify specific value; Once you added it to object TextField become scrollable)_.
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of TextField Component. 
    - **y** : Y-Coordinate of TextField Component. 
    - **width** : X-Coordinate of TextField Component. 
    - **height** : X-Coordinate of TextField Component. 
  - **layout-align** : It is used to set the position of TextField component while using border layout. 
  - **global** : It is used to make TextField variable public. 
  - **enable** : It is used to enable or disable TextField component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "textfield",
        "id": "textField",
        "text": "enter message",
        "rows": 5,
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "pos": {
            "x": 10,
            "y": 10,
            "width": 30,
            "height": 10 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  

      {
        "type": "textfield",
        "id": "textfield",
        "text": "enter message",
        "columns": 10,
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "align": "center",
        "scroll-pane": true,
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "layout-align": "south",
        "global": true,
        "enable": true
      }

#### RadioButton
    
      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for radioButton component. 
  - **text** : It is used to set the text of radioButton. 
  - **icon** : It is used to set the icon of radioButton. 
  - **foreground-color** : It is used to set the foreground color of radioButton. 
  - **background-color** : It is used to set the background color of radioButton. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of radioButton Component. 
    - **y** : Y-Coordinate of radioButton Component. 
    - **width** : X-Coordinate of radioButton Component. 
    - **height** : X-Coordinate of radioButton Component. 
  - **layout-align** : It is used to set the position of radioButton component while using border layout. 
  - **global** : It is used to make radioButton variable public. 
  - **enable** : It is used to enable or disable radioButton component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "radioButton",
        "id": "radioButton",
        "icon": "iceButton.png",
        "text": "Ice",
        "foreground-color": [10, 10, 50],
        "background-color": [50, 10, 10],
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "pos": {
            "x": 10,
            "y": 10,
            "width": 30,
            "height": 10 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  

      {
        "type": "radioButton",
        "id": "radioButton",
        "icon": "iceButton.png",
        "text": "Ice",
        "font": {
          "name": "arial",
          "size": 20,
          "style": "bold"
        },
        "layout-align": "center",
        "global": true,
        "enable": true
      }

#### RadioGroup
    
      {
        "type": String,
        "id": String,
        "buttons": [
            {
                "type": "checkbox",
                    .            
                    .            
                    .            
            },
            {
                "type": "radio",
                    .            
                    .            
                    .            
            }
        ],
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for radioGroup component. 
  - **buttons** : It is an array of radioButton or checkbox. 
  - **global** : It is used to make radioGroup variable public. 
  - **enable** : It is used to enable or disable radioGroup component. 
        
       
  **Example**  
  
      {
        "type": "radiogroup",
        "global": true,
        "buttons": [
          {
            "type": "checkbox",
            "text": "CHECKBOX_2",
            "font": {
              "name":"arial",
              "size": 30,
              "style": "plain"
            },
            "global": true,
            "foreground-color": [255, 0, 255],
            "background-color": [0, 0, 255]
          },
          {
            "type": "radio",
            "text": "RadioButton_2",
            "font": {
              "name":"arial",
              "size": 30,
              "style": "plain"
            },
            "global": true,
            "foreground-color": [255, 0, 255],
            "background-color": [0, 0, 255]
          }
        ],
        "global": true,
        "enable": true
      }

#### ScrollPane

   ScrollPane component is use as a property of object for other component for ease of use. 

    "scroll-pane": {
        "vertical": Boolean,
        "horizontal": Boolean
    },
   
   Example :

      {
        "type": "panel",
        "scroll-pane": {
        	"vertical":true,
        	"horizontal":false
        },
        "components": [
          {
            "type": "button",
            "id": "press2",
            "text": "press2_fore",
            "icon": "icon.png",
            "foreground-color": [100, 20, 200],
            "global": true
          },
          {
            "type": "list",
            "id": "list2",
            "data": ["list2", "list1", "list0"],
            "font": {
              "name":"arial",
              "size": 10,
              "style": "plain"
            },
            "background-color": [0, 0, 255],
            "foreground-color": [100, 20, 200],
            "selection-foreground-color": [0, 200, 0],
            "selection-background-color": [0, 200, 255],
            "width": 100,
            "height": 100,
            "global": true
          }
        ]
      },
  
#### Slider
    
      {
        "type": String,
        "id": String,
        "val": Integer,
        "min": Integer,
        "max": Integer,
        "orientation": String,
        "inverted": Boolean,
        "paintLabels": Boolean,
        "paintTicks": Boolean,
        "paintTrack": Boolean,
        "minorTickSpacing": Integer,
        "majorTickSpacing": Integer,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for slider component. 
  - **val** : It is used to set the value of slider. 
  - **min** : It is used to set the minimum value of slider. 
  - **max** : It is used to set the maximum value of slider. 
  - **orientation** : It is used to set the orientation of slider It can be horizontal or vertical. 
  - **inverted** : It is used to invert the slider. 
  - **paintLabels** : It is used to show label of the slider. 
  - **paintTicks** : It is used to show ticks of the slider. 
  - **paintTrack** : It is used to show track of the slider. 
  - **minorTickSpacing** : It is used to set minor Tick Spacing of the slider. 
  - **majorTickSpacing** : It is used to set major Tick Spacing of the slider. 
  - **foreground-color** : It is used to set the foreground color of slider. 
  - **background-color** : It is used to set the background color of slider. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of slider Component. 
    - **y** : Y-Coordinate of slider Component. 
    - **width** : X-Coordinate of slider Component. 
    - **height** : X-Coordinate of slider Component. 
  - **layout-align** : It is used to set the position of slider component while using border layout. 
  - **global** : It is used to make slider variable public. 
  - **enable** : It is used to enable or disable slider component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "slider",
        "min": 0,
        "max": 100,
        "orientation": "horizontal",
        "paintLabels": true,
        "paintTicks": true,
        "minorTickSpacing": 1,
        "majorTickSpacing": 10
        "pos": {
            "x": 10,
            "y": 10,
            "width": 30,
            "height": 10 
        },
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  


      {
        "type": "slider",
        "min": 0,
        "max": 100,
        "orientation": "horizontal",
        "paintLabels": true,
        "paintTicks": true,
        "minorTickSpacing": 1,
        "majorTickSpacing": 10

      }

#### Table
    
      {
        "type": String,
        "id": String,
        "rows": Array,
        "columns": Array,
        "resize": Boolean,
        "compact-scroll": Boolean,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "scroll-pane": Object/Boolean,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for table component. 
  - **rows** : It is used to set the number of rows of table. 
  - **columns** : It is used to set the number of columns of table. 
  - **resize** : It is used to make table resizeable. 
  - **compact-scroll** : It is used to set the preferred size of the viewport for the table.
  - **foreground-color** : It is used to set the foreground color of table. 
  - **background-color** : It is used to set the background color of table. 
  - **scroll-pane** : It is used to make table scrollable _(Note:No need to specify specific value; Once you added it to object of table then table become scrollable)_.
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of table Component. 
    - **y** : Y-Coordinate of table Component. 
    - **width** : X-Coordinate of table Component. 
    - **height** : X-Coordinate of table Component. 
  - **layout-align** : It is used to set the position of table component while using border layout. 
  - **global** : It is used to make table variable public. 
  - **enable** : It is used to enable or disable table component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "table",
        "columns": ["Name", "Age", "Salary"],
        "rows": [
          ["ABC", "18", "99"],
          ["A2C", "218", "919"],
          ["A23BC", "128", "9329"]
        ],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "pos": {
            "x": 100,
            "y": 100,
            "width": 200,
            "height": 100 
        },
        "resize": false,
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  
  

      {
        "type": "table",
        "columns": ["Name", "Age", "Salary"],
        "rows": [
          ["ABC", "18", "99"],
          ["A2C", "218", "919"],
          ["A23BC", "128", "9329"]
        ],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "resize": false,
        "layout-align": "south",
        "global": true,
        "enable": true
      }

#### ProgressBar
    
      {
        "type": String,
        "id": String,
        "val": Integer,
        "min": Integer,
        "max": Integer,
        "text": String,
        "orientation": String,
        "indeterminate": Boolean,
        "string-painted": Boolean,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "pos": {
            "x": Integer,
            "y": Integer,
            "width": Integer,
            "height": Integer 
        },
        "layout-align": String,
        "global": Boolean,
        "enable": Boolean
      }
  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for progressbar component. 
  - **val** : It is used to set the value of progressbar. 
  - **min** : It is used to set the minimum value of progressbar. 
  - **max** : It is used to set the maximum value of progressbar. 
  - **text** : It is used to set the text value of progressbar. 
  - **orientation** : It is used to set the orientation of progressbar It can be horizontal or vertical. 
  - **indeterminate** : It is used to set the progressbar bar in determinate or indeterminate mode. 
  - **string-painted** : It is used to show string of the progressbar. 
  - **foreground-color** : It is used to set the foreground color of progressbar. 
  - **background-color** : It is used to set the background color of progressbar. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **pos** : It is used when we want to set component by x and y coordinates.
    - **x** : X-Coordinate of progressbar Component. 
    - **y** : Y-Coordinate of progressbar Component. 
    - **width** : X-Coordinate of progressbar Component. 
    - **height** : X-Coordinate of progressbar Component. 
  - **layout-align** : It is used to set the position of progressbar component while using border layout. 
  - **global** : It is used to make progressbar variable public. 
  - **enable** : It is used to enable or disable progressbar component. 
        
       
  **Example**  
    1. Using position. 
  
      {
        "type": "progressbar",
        "id": "progressState",
        "val": 10,
        "min": 0,
        "max": 100,
        "text": "10%",
        "orientation": "horizontal",
        "indeterminate": false,
        "string-painted": true,
        "foreground-color": [0, 0, 0],
        "background-color": [5, 10, 50],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "pos": {
            "x": 10,
            "y": 10,
            "width": 100,
            "height": 20 
        },
        "layout-align": "south",
        "global": true,
        "enable": true
      }

  2. Using Layout Align.  


      {
        "type": "progressbar",
        "id": "progressState",
        "val": 10,
        "min": 0,
        "max": 100,
        "text": "10%",
        "orientation": "horizontal",
        "indeterminate": false,
        "string-painted": true,
        "foreground-color": [0, 0, 0],
        "background-color": [5, 10, 50],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "layout-align": "south",
        "global": true,
        "enable": true
      }

#### Menu and Its Items


##### MenuItem

      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "global": Boolean,
        "enable": Boolean
      }

  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for the menu item component. 
  - **text** : It is used to set the text for the menu item component. 
  - **icon** : It is used to set the icon for the menu item component. 
  - **foreground-color** : It is used to set the foreground color of menu item. 
  - **background-color** : It is used to set the background color of menu item. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **global** : It is used to make menu item variable public. 
  - **enable** : It is used to enable or disable the menu item component. 

  **Example**  

      {
        "type": "item",
        "id": "menuitem",
        "text": "First",
        "icon": "first.png",
        "foreground-color": [10, 0, 40],
        "background-color": "black",
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "global": true,
        "enable": true
      }

##### CheckboxMenuItem
   For checkboxMenuItem refer to [checkbox](#checkbox)

##### RadioButtonMenuItem
   For radioButtonMenuItem refer to [radio](#radiobutton) 

##### RadioGroupMenuItem
   for radioGroupMenuItem refer to [radioGroup](#radiogroup)

##### Menu

      {
        "type": String,
        "id": String,
        "text": String,
        "icon": String,
        "items": Array,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "global": Boolean,
        "enable": Boolean
      }

  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for the menu component. 
  - **text** : It is used to set the text for the menu component. 
  - **icon** : It is used to set the icon for the menu component. 
  - **items** : It is used to add the sub components to the menu component. 
  - **foreground-color** : It is used to set the foreground color of menu. 
  - **background-color** : It is used to set the background color of menu. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **global** : It is used to make menu item variable public. 
  - **enable** : It is used to enable or disable the menu component. 

  **Example**  

      {
        "type": "menu",
        "id": "menuFirst",
        "text": "First",
        "icon": "first.png",
        "items": [
          {
            "type": "item",
            "text": "First Item",
            "foreground-color": "red",
            "background-color": "blue"
          },
          {
            "type": "radiogroup",
            "global": true,
            "buttons": [
              {
                "type": "checkbox",
                "text": "Senconds First Item",
                "font": {
                  "name":"arial",
                  "size": 30,
                  "style": "plain"
                },
                "global": true,
                "foreground-color": [255, 0, 255],
                "background-color": [0, 0, 255]
              },
              {
                "type": "radio",
                "text": "Senconds Second Item",
                "font": {
                  "name":"arial",
                  "size": 30,
                  "style": "plain"
                },
                "global": true,
                "foreground-color": [255, 0, 255],
                "background-color": [0, 0, 255]
              }
            ]
          },
        ],
        "foreground-color": [10, 5, 50],
        "background-color": [120, 110, 150],
        "font": {
          "name":"arial",
          "size": 10,
          "style": "plain"
        },
        "global": true,
        "enable": true
      }


##### MenuBar

      {
        "type": String,
        "id": String,
        "menus": Array,
        "foreground-color": Array/String,
        "background-color": Array/String,
        "font": {
          "name": String,
          "size": Integer,
          "style": String
        },
        "global": Boolean,
        "enable": Boolean
      }

  Above properties are specified below :     
  - **type** : It is used to set the type of component. 
  - **id** : It is used to set the variable name for the menubar component. 
  - **menus** : It is used to add the sub menu components to the menubar component. 
  - **foreground-color** : It is used to set the foreground color of menubar. 
  - **background-color** : It is used to set the background color of menubar. 
  - **font** : It is used to set font.
    - **name** : Font Name. 
    - **size** : Size of font. 
    - **style** : style of font (plain, bold, italic, bold+italic). 
  - **global** : It is used to make menubar item variable public. 
  - **enable** : It is used to enable or disable the menubar component. 

  **Example**  

      {
        "type":"menubar",
        "background-color": "black",
        "menus": [
          {
            "type": "menu",
            "text": "First Menu",
            "icon": "icon.png",
            "foreground-color": "blue",
            "background-color": "red",
            "items": [
              {
                "type": "item",
                "text": "First Item",
                "foreground-color": "red",
                "background-color": "blue"
              },
              {
                "type": "menu",
                "text": "Sub Menu",
                "foreground-color": "blue",
                "background-color": "red",
                "items": [
                  {
                    "type": "item",
                    "text": "First Item",
                    "foreground-color": "red",
                    "background-color": "blue"
                  },
                  {
                    "type": "menu",
                    "text": "Sub Menu",
                    "foreground-color": "blue",
                    "background-color": "red",
                    "items": [
                      {
                        "type": "item",
                        "text": "First Item",
                        "foreground-color": "red",
                        "background-color": "blue"
                      },
                      {
                        "type": "radiogroup",
                        "global": true,
                        "buttons": [
                          {
                            "type": "checkbox",
                            "text": "CHECKBOX_2",
                            "font": {
                              "name":"arial",
                              "size": 30,
                              "style": "plain"
                            },
                            "global": true,
                            "foreground-color": [255, 0, 255],
                            "background-color": [0, 0, 255]
                          },
                          {
                            "type": "radio",
                            "text": "RadioButton_2",
                            "font": {
                              "name":"arial",
                              "size": 30,
                              "style": "plain"
                            },
                            "global": true,
                            "foreground-color": [255, 0, 255],
                            "background-color": [0, 0, 255]
                          }
                        ]
                      },
                      {
                        "type": "menu",
                        "text": "Sub Menu",
                        "foreground-color": "blue",
                        "background-color": "red",
                        "items": [
                          {
                            "type": "item",
                            "text": "First Item",
                            "foreground-color": "red",
                            "background-color": "blue"
                          },
                          {
                            "type": "radio",
                            "text": "RadioButton",
                            "font": {
                              "name":"arial",
                              "size": 30,
                              "style": "plain"
                            },
                            "global": true,
                            "foreground-color": [255, 0, 255],
                            "background-color": [0, 0, 255]
                          }
                        ]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
