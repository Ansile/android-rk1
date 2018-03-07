package com.example.ansile.rk1

import android.app.FragmentManager
import android.content.res.Configuration
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import ru.mail.park.articlelistlib.Article
import ru.mail.park.articlelistlib.ArticleListFragment


class MainActivity : AppCompatActivity(), NewsSingleFragment.OnFragmentInteractionListener {

    lateinit var articleList: ArticleListFragment
    lateinit var newsFragment: NewsSingleFragment
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("orientation", Integer.toString(resources.configuration.orientation))

        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        articleList = ArticleListFragment()

        fragmentTransaction.replace(R.id.ParentLayout, articleList)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentManager = supportFragmentManager
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        articleList.setOnArticleClickListener {
            this.displayArticle(it)
        }

        fragmentTransaction.commit()

        article = savedInstanceState?.getSerializable("article") as Article?

        article?.let { displayArticle(it) }
    }

    fun displayArticle(article: Article) {
        this.article = article
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val data = Bundle()

        data.putSerializable("article", article)

        newsFragment = NewsSingleFragment()
        newsFragment.arguments = data

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction.addToBackStack("ListToSingle")
            fragmentTransaction.replace(R.id.ParentLayout, newsFragment)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction.replace(R.id.ArticleLayout, newsFragment)
        }

        fragmentTransaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (article != null) {
            outState.putSerializable("article", article)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
