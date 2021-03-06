/* $Id: util.cpp,v 1.3 2011-03-01 18:24:31-08 dmfrank - $
 * Derek Frank, dmfrank@ucsc.edu
 *
 * NAME
 *   util - implementation file
 */

#include <cerrno>
#include <cstdlib>
#include <stdexcept>
#include <typeinfo>

#include <sstream>

using namespace std;

list_exn::list_exn (const string &what): runtime_error (what) {
   complain() << what << endl;
}

const string octal (int decimal) {
   ostringstream ostring;
   ostring.setf (ios::oct);
   ostring << decimal;
   return ostring.str ();
}

int sys_info::exit_status = EXIT_SUCCESS;
string sys_info::execname; // Must be initialized from main().

void sys_info::set_execname (const string &argv0) {
   execname = argv0;
   cout << boolalpha;
   cerr << boolalpha;
}

ostream &complain() {
   sys_info::set_status (EXIT_FAILURE);
   cerr << sys_info::get_execname () << ": ";
   return cerr;
}

template <typename T>
string to_string (const T &that) {
   ostringstream stream;
   stream << that;
   return stream.str ();
}

template <typename T>
T from_string (const string &that) {
   stringstream stream;
   stream << that;
   T result;
   if ( !(stream >> result   // Can we read type from string?
       && stream >> std::ws  // Flush trailing white space.
       && stream.eof ())     // Must now be at end of stream.
   ) {
      throw domain_error (string (typeid (T).name ())
            + " from_string (" + that + ")");
   }
   return result;
}
