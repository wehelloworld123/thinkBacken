package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.personal.Suggestion;

import java.util.List;

public interface SuggestService extends IService<Suggestion> {
    int SaveSuggestion(Suggestion data);

    List<Suggestion> GetNewSuggestions(int start);

    List<Suggestion> GetOldSuggestions(int start);
}
