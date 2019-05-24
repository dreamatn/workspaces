package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCEX;
import com.hisun.BP.BPCFAMTA;
import com.hisun.BP.BPCFTLAM;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPRTRT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNZSSUBS {
    boolean pgmRtn = false;
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_COL_CNT = 18;
    String K_FMT_ID = "LN520";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    LNZSSUBS_WS_MSGID WS_MSGID = new LNZSSUBS_WS_MSGID();
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    LNZSSUBS_WS_FMT_OUT WS_FMT_OUT = new LNZSSUBS_WS_FMT_OUT();
    LNZSSUBS_WS_SSUBS_PROJ_NO WS_SSUBS_PROJ_NO = new LNZSSUBS_WS_SSUBS_PROJ_NO();
    LNZSSUBS_WS_DATE WS_DATE = new LNZSSUBS_WS_DATE();
    LNZSSUBS_WS_OUT_HEAD WS_OUT_HEAD = new LNZSSUBS_WS_OUT_HEAD();
    List<LNZSSUBS_WS_MPAG_OUT> WS_MPAG_OUT = new ArrayList<LNZSSUBS_WS_MPAG_OUT>();
    char WS_REC_PACT_FLG = ' ';
    char WS_REC_RELA_FLG = ' ';
    String WS_TX_CD = " ";
    char WS_FND_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCEX BPCEX = new BPCEX();
    BPRTRT BPRTRTT = new BPRTRT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    CICACCU CICACCU = new CICACCU();
    LNRRELA LNRRELA = new LNRRELA();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICCUST CICCUST = new CICCUST();
    int WS_CNT2 = 0;
    int WS_VAL_DATE = 0;
    int WS_START_NUM = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCAWAC SCCAWAC;
    LNCSSUBS LNCSSUBS;
    public void MP(SCCGWA SCCGWA, LNCSSUBS LNCSSUBS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSUBS = LNCSSUBS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSUBS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        WS_TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, LNCSSUBS.TYP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_INPUT();
        if (pgmRtn) return;
        if (LNCSSUBS.FUNC == 'B') {
            B010_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'A') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'M') {
            B040_MODIFY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'I') {
            B050_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSSUBS.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNCSSUBS.FUNC == 'A') {
            IBS.init(SCCGWA, BPCSGSEQ);
            BPCSGSEQ.TYPE = "SEQ";
            BPCSGSEQ.CODE = "LNSUBS";
            BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSGSEQ.RUN_MODE = 'O';
            S000_CALL_BPZSGSEQ();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_SSUBS_PROJ_NO.WS_SSUBS_PROJ_YYYY = 0;
            else WS_SSUBS_PROJ_NO.WS_SSUBS_PROJ_YYYY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1).trim().length() == 0) WS_SSUBS_PROJ_NO.WS_SSUBS_PROJ_NNNNN = 0;
            else WS_SSUBS_PROJ_NO.WS_SSUBS_PROJ_NNNNN = Integer.parseInt(JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1));
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_SSUBS_PROJ_NO);
            LNCSSUBS.DATA.KEY.PROJ_NO = Integer.parseInt(JIBS_tmp_str[0]);
            CEP.TRC(SCCGWA, LNCSSUBS.DATA.KEY.PROJ_NO);
        }
        IBS.init(SCCGWA, LNRSUBS);
        IBS.init(SCCGWA, LNRRELA);
        LNRSUBS.KEY.PROJ_NO = LNCSSUBS.DATA.KEY.PROJ_NO;
        if (LNCSSUBS.FUNC == 'A') {
            B010_VALID_DATA_CHECK();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'M') {
            T000_STARTBR_LNTRELA();
            if (pgmRtn) return;
            T000_READNEXT_LNTRELA();
            if (pgmRtn) return;
            if (WS_FND_FLG == 'Y') {
                B020_VALID_DATA_CHECK();
                if (pgmRtn) return;
            }
            if (WS_FND_FLG == 'N') {
                B010_VALID_DATA_CHECK();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTRELA();
            if (pgmRtn) return;
        } else if (LNCSSUBS.FUNC == 'D') {
            LNRRELA.PROJ_NO = LNCSSUBS.DATA.KEY.PROJ_NO;
            LNRRELA.KEY.TYPE = 'S';
            T000_READ_LNTRELA();
            if (pgmRtn) return;
            if (WS_REC_RELA_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RELA_SUBS_RELATE, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B010_VALID_DATA_CHECK() throws IOException,SQLException,Exception {
        if (LNCSSUBS.SUBS_MTH == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSUBS.SUBS_MTH != '1' 
            && LNCSSUBS.SUBS_MTH != '2' 
            && LNCSSUBS.SUBS_MTH != '3' 
            && LNCSSUBS.SUBS_MTH != '4' 
            && LNCSSUBS.SUBS_MTH != '5' 
            && LNCSSUBS.SUBS_MTH != '6') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXCEED, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSUBS.SUBS_MTH == '2' 
            || LNCSSUBS.SUBS_MTH == '6') {
            if (LNCSSUBS.SUBS_RAT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RAT_SPACE_O_ZERO, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSUBS.SUBS_RAT < 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATE_LT_ZERO, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCSSUBS.SUBS_MTH == '3') {
            if (LNCSSUBS.SUBS_PCT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PCT_SPACE_O_ZERO, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSUBS.SUBS_PCT > 100) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_PCT_GT_100, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSUBS.AMT_TYP == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_TYP_M_INPUT, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSSUBS.AMT_TYP != '1' 
                && LNCSSUBS.AMT_TYP != '2' 
                && LNCSSUBS.AMT_TYP != '3') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXCEED, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCSSUBS.SUBS_MTH == '5' 
            || LNCSSUBS.SUBS_MTH == '4') {
            if (LNCSSUBS.FIXED_AMT == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_SPACE_O_ZERO, WS_MSGID);
                WS_FLD_NO = 0;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCSSUBS.TERM == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSUBS.ST_TERM == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSSUBS.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_VALID_DATA_CHECK() throws IOException,SQLException,Exception {
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
        if (LNCSSUBS.TYP == LNRSUBS.TYP 
            && LNCSSUBS.TERM == LNRSUBS.TERM 
            && LNCSSUBS.CCY.equalsIgnoreCase(LNRSUBS.CCY) 
            && LNCSSUBS.FIXED_AMT == LNRSUBS.FIXED_AMT 
            && LNCSSUBS.SUBS_PCT == LNRSUBS.SUBS_PCT 
            && LNCSSUBS.AMT_TYP == LNRSUBS.AMT_TYP 
            && LNCSSUBS.SUBS_RAT == LNRSUBS.SUBS_RAT 
            && LNCSSUBS.CI_NO.equalsIgnoreCase(LNRSUBS.CI_NO)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_WAR_NEED_AUTH, WS_MSGID);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_HEAD);
        if (LNCSSUBS.PAGE_NUM == 0) {
            WS_DATE.WS_CURR_PAGE = 1;
        } else {
            WS_DATE.WS_CURR_PAGE = (short) LNCSSUBS.PAGE_NUM;
        }
        WS_OUT_HEAD.WS_O_CURR_PAGE = WS_DATE.WS_CURR_PAGE;
        WS_DATE.WS_LAST_PAGE = 'N';
        WS_DATE.WS_PAGE_ROW = (short) LNCSSUBS.PAGE_ROW;
        WS_DATE.WS_NEXT_START_NUM = ( ( WS_DATE.WS_CURR_PAGE - 1 ) * WS_DATE.WS_PAGE_ROW ) + 1;
        WS_START_NUM = WS_DATE.WS_NEXT_START_NUM;
        LNRSUBS.KEY.PROJ_NO = LNCSSUBS.DATA.KEY.PROJ_NO;
        LNRSUBS.CI_NO = LNCSSUBS.CI_NO;
        if (LNCSSUBS.DATA.KEY.PROJ_NO == 0) {
