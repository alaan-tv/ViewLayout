# LayoutEngine
Module provides the implementation of themes and layout concept of dee.cms.
## Themes
Theme is how the overall site looks and feels, usually themes is a container of the following items:
- **template**: or templates of the master site, defines main look and feel of the site and adds scripting on the template level, the template defines:
    - Template Script: using template language ( freemarker for now) to template the main site HTML structure and define components( Navigation Component, Live TV Component ... etc) and containers on it
    - Template Script for AMP: defines the master template using template language of the AMP version of the site.
- **Script**: which as :
    - external URL of javascript libraries or javascript code to run within for the master template. 
    - the css selector defines where the script will be injected, where prefix `+` for append or `- for preappend used to state where the injection point is ( after the selected node or before)
- **CSS**: CSS script writen in SASS language to style the template.

## Container/Component template
Each container or component render itself and customizable by using a configuration on the container/theme lever,
In order to make front-end decoupled, each component/container has its own (template, css, javascript, and assets), 