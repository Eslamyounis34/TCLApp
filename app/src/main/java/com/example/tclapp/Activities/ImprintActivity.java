package com.example.tclapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tclapp.R;

public class ImprintActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarTitle,imprintTx;
    ImageView toolbarIconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprint);

        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        toolbarIconBack=findViewById(R.id.backicon);
        imprintTx=findViewById(R.id.imprint_text);

        toolbarTitle.setText("Imprint");
        toolbarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imprintTx.setText("1. Contents of the online offer\n" +
                "Author assumes no kind of warranty for the topicality, correctness, completeness or quality of the information provided. Claims from liability against Author related to damage of a substantive or ideal nature caused by the use or non-use of the information provided or by the use of defective and incomplete information have been ruled out as a matter of principle to the extent that no provable deliberate or grossly negligent culpability exists on the part of Author.\n" +
                        "\n" +
                        "All offers shall be subject to change without notice and non-binding. Author expressly reserves the right to amend, supplement or delete parts of the sites or the entire offer without specific announcement or to cease publication temporarily or permanently.\n" +
                        "\n" +
                        "2. References and links\n" +
                        "In the case of direct or indirect links to outside websites (\"hyperlinks\") no longer within Author’s sphere of responsibility, an obligation to liability would exclusively come into existence in the event of Author having knowledge of the contents and it being technically possible and reasonably to be expected that he prevents their use in the event of illicit contents.\n" +
                        "\n" +
                        "Author hereby expressly declares that no illegal contents were recognisable on the sites to be linked at the time of setting the link. Author has no kind of influence on the current and future design, contents or authorship of the linked/connected sites. Therefore, he hereby expressly disassociates himself from all the contents of all linked/connected sizes amended after the setting of the link. This establishment applies to all links and references set within his own Internet offer and also to outside entries in guest books, discussion forums, lists of links, mailing lists set up by Author and in all other forms of databases, to the contents of which external writing access is possible. Merely the provider of the site to which reference is made, not the party merely referring to the publication in question via links is liable for illegal, defective or incomplete contents and in particular for damage incurred as a result of use or non-use of information provided in such a way.\n" +
                        "\n" +
                        "3. Copyright and trademark rights\n" +
                        "Author endeavours to observe the copyrights of the graphics, sound documents, video sequences and texts used, to make use of graphics, sound documents, video sequences and texts he has produced himself or to have recourse to licence-free graphics, sound documents, video sequences and texts in all publications.\n" +
                        "\n" +
                        "All brand names and trademarks stated within the Internet offer and, if applicable, protected by third parties are unrestrictedly subject to the provisions of the trademark law valid at the time and the ownership rights of the registered owners in question. A mere naming does not give rise to the conclusion that brand names have not been protected by third parties’ rights!\n" +
                        "\n" +
                        "The copyright for published objects produced by Author personally shall remain with the author of the sites alone. Reproduction or use of such graphics, sound documents, video sequences and texts in other electronic or printed publications shall not be permitted without Author’s express consent.\n" +
                        "\n" +
                        "4. Data protection\n" +
                        "To the extent that the possibility of entering personal or business data (e-mail addresses, names, addresses) exists within the Internet offer, disclosure of said data on the part of the user shall be on an expressly voluntary basis. Use of and payment for all the services offered is also permitted without statement of such data or by use of anonymised data or a pseudonym - to the extent technically possible and reasonably to be expected. Use of the contact data published within the framework of the imprint or comparable information such as postal addresses, telephone and fax numbers as well as e-mail addresses by third parties for the transmission of information not expressly requested is not permitted. The right to legal steps against the dispatchers of so-called spam mails in breach of this ban shall remain expressly reserved.\n" +
                        "\n" +
                        "5. Legal effectivity of this disclaimer\n" +

                        "This disclaimer is to be regarded as a part of the Internet offer from which reference has been made to this site. To the extent that parts of individual formulations of this text do not, no longer or incompletely correspond to the valid legal situation, the content and validity of the remaining parts of the document shall remain unaffected.");

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
