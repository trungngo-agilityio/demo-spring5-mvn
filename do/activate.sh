
# The git repository that do installed from.
# https://github.com/agilityio/oh-my-ops
DO_INSTALL_REPO="agilityio/oh-my-ops"

# Uncomment this if you want to run with a specific version.
DO_VERSION="0.1.2"

# The array of plugins to enabled.
DO_PLUGINS="proj git prompt banner full mongo mvn"

# The environments supported.
DO_ENVS="local dev stag prod"

cd do
source "src/init.sh"
cd ..

_do_proj "." "proj"
_do_full_proj "proj"

# Enables git command at the project root.
_do_git "proj"

_do_repo "src" "src"
_do_mvn "src" 

_do_repo "src/starter" "starter"
_do_mvn "starter" 


# Enables mongodb supports
_do_mongo "proj"

_do_banner



