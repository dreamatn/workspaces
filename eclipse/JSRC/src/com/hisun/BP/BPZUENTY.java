package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQITM;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZUENTY {
    boolean pgmRtn = false;
    String K_BP_MMO = "BP";
    int K_EXP_DATE = 99991231;
    String K_COPYBOOK_HIS_ENT = "BPCHENT";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_NEXT = 0;
    short WS_INSERT_POS = 0;
    short WS_REMAIN_CNT = 0;
    short WS_CHECK_CNT = 0;
    short WS_OLD_SORT = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_F = 0;
    int WS_J = 0;
    String WS_MOD_NO = " ";
    short WS_FIRST_VALUE = 0;
    short WS_SECOND_VALUE = 0;
    int WS_EFF_DATE = 0;
    String WS_COA_FLG = " ";
    String WS_HISTORY_RMK = " ";
    BPZUENTY_WS_ENTY_KEY WS_ENTY_KEY = new BPZUENTY_WS_ENTY_KEY();
    short WS_ENTY_LAST_BEGSEQ = 0;
    char WS_MAINTAIN_FLG = ' ';
    char WS_CHECK_FLG = ' ';
    char WS_EVENT_FLG = ' ';
    char WS_AMT_PNT_FLG = ' ';
    char WS_FUNC_FLG = ' ';
    char WS_AITENTY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRACM BPRACM = new BPRACM();
    BPCPITM BPCPITM = new BPCPITM();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCHENT BPCHENT = new BPCHENT();
    BPCHENT BPCHENTO = new BPCHENT();
    BPCHENT BPCHENTN = new BPCHENT();
    AIRENTY AIRENTY = new AIRENTY();
    AICENTY AICMENTY = new AICENTY();
    BPCPRMBR BPCPRMBR = new BPCPRMBR();
    SCCGWA SCCGWA;
    BPCUENTY BPCUENTY;
    BPRENTB BPRENTB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    public void MP(SCCGWA SCCGWA, BPCUENTY BPCUENTY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUENTY = BPCUENTY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUENTY return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        BPCUENTY.RC.RC_MMO = K_BP_MMO;
        BPCUENTY.RC.RC_CODE = 0;
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        R000_SET_LK_MMT();
        if (pgmRtn) return;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "YYYYYYYYYYYYYYYYYYYYYYYYYYY:");
        CEP.TRC(SCCGWA, BPCUENTY.INPUT_DATA.CCY_PNT);
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (BPCUENTY.INPUT_DATA.FUNC == 'A'
            || BPCUENTY.INPUT_DATA.FUNC == 'C') {
            B020_ADD_WHOLE_PROC();
            if (pgmRtn) return;
            B090_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (BPCUENTY.INPUT_DATA.FUNC == 'D') {
            B040_DEL_WHOLE_PROC();
            if (pgmRtn) return;
        } else if (BPCUENTY.INPUT_DATA.FUNC == 'M') {
            B050_MODIFY_DETAIL_PROC();
            if (pgmRtn) return;
            B090_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (BPCUENTY.INPUT_DATA.FUNC == 'N') {
            B060_DELETE_DETAIL_PROC();
            if (pgmRtn) return;
            B090_HISTORY_PROC();
            if (pgmRtn) return;
        } else if (BPCUENTY.INPUT_DATA.FUNC == 'E') {
            B070_INQ_DETAIL_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if ((BPCUENTY.INPUT_DATA.FUNC != 'A' 
            && BPCUENTY.INPUT_DATA.FUNC != 'U' 
            && BPCUENTY.INPUT_DATA.FUNC != 'D' 
            && BPCUENTY.INPUT_DATA.FUNC != 'I' 
            && BPCUENTY.INPUT_DATA.FUNC != 'C' 
            && BPCUENTY.INPUT_DATA.FUNC != 'M' 
            && BPCUENTY.INPUT_DATA.FUNC != 'N' 
            && BPCUENTY.INPUT_DATA.FUNC != 'E')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUENTY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUENTY.INPUT_DATA.FUNC == 'A' 
            || BPCUENTY.INPUT_DATA.FUNC == 'C' 
            || BPCUENTY.INPUT_DATA.FUNC == 'M') {
            R000_CHECK_ACMODEL_EVENT();
            if (pgmRtn) return;
            R000_CHECK_GL_BOOK();
            if (pgmRtn) return;
            if (BPCUENTY.INPUT_DATA.ITM_CODE.trim().length() > 0) {
                R000_CHECK_ITEM_CODE();
                if (pgmRtn) return;
            }
            if (BPCUENTY.INPUT_DATA.BR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = BPCUENTY.INPUT_DATA.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
        if (BPCUENTY.INPUT_DATA.FUNC == 'C' 
            || BPCUENTY.INPUT_DATA.FUNC == 'M' 
            || BPCUENTY.INPUT_DATA.FUNC == 'N' 
            || BPCUENTY.INPUT_DATA.FUNC == 'D') {
            R000_CHECK_UPD_TIME();
            if (pgmRtn) return;
        }
    }
    public void B020_ADD_WHOLE_PROC() throws IOException,SQLException,Exception {
        R000_TRANS_DATA();
        if (pgmRtn) return;
        T000_WRITE_AITENTY();
        if (pgmRtn) return;
        R000_TRANS_HISTORY_NEW();
        if (pgmRtn) return;
    }
    public void B040_DEL_WHOLE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRENTY);
        AIRENTY.KEY.GL_BOOK = BPCUENTY.INPUT_DATA.GL_BOOK;
        AIRENTY.KEY.MODNO = BPCUENTY.INPUT_DATA.MODNO;
        AIRENTY.KEY.EVENT_TYPE = BPCUENTY.INPUT_DATA.EVENT_TYPE;
        AIRENTY.KEY.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRENTY.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, WS_ENTY_KEY);
        WS_ENTY_KEY.WS_K_GL_BOOK = BPCUENTY.INPUT_DATA.GL_BOOK;
        WS_ENTY_KEY.WS_K_MODNO = BPCUENTY.INPUT_DATA.MODNO;
        WS_ENTY_KEY.WS_K_EVENT_TYPE = BPCUENTY.INPUT_DATA.EVENT_TYPE;
        T000_STARTBR_AITENTY();
        if (pgmRtn) return;
        T000_READNEXT_AITENTY();
        if (pgmRtn) return;
        while (WS_AITENTY_FLG != 'N') {
            T000_DELETE_AITENTY();
            if (pgmRtn) return;
            T000_READNEXT_AITENTY();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITENTY();
        if (pgmRtn) return;
    }
    public void B070_INQ_DETAIL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRENTY);
        IBS.init(SCCGWA, AICMENTY);
        AIRENTY.KEY.GL_BOOK = BPCUENTY.INPUT_DATA.GL_BOOK;
        AIRENTY.KEY.MODNO = BPCUENTY.INPUT_DATA.MODNO;
        AIRENTY.KEY.EVENT_TYPE = BPCUENTY.INPUT_DATA.EVENT_TYPE;
        AIRENTY.KEY.SORT_SEQ = BPCUENTY.INPUT_DATA.SORT_SEQ;
        AIRENTY.KEY.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRENTY.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_READ_AITENTY();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRENTY);
