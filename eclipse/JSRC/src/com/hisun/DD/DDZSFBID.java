package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCTPCL;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSFBID {
    int JIBS_tmp_int;
    DBParm DCTIAACR_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTHLD_RD;
    DBParm DDTFBID_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTIAMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTNOSI_RD;
    brParm DDTCCY_BR = new brParm();
    brParm TDTSMST_BR = new brParm();
    DBParm DDTHLDR_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD560";
    String K_OUTPUT_FMT1 = "DD561";
    String K_OUTPUT_FMT3 = "SCZ01";
    String PGM_SCZGSEQ = "SCZGSEQ";
    String K_CHK_STS_TBL = "5602";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_STS_TABLE_APP1 = "DD";
    String K_STS_TABLE_APP2 = "TD";
    String K_STS_TABLE_APP3 = "DC";
    String K_HIS_REMARKS1 = "SET AC STOP STATUS";
    String K_HIS_REMARKS2 = "SET AC ONLY DEBIT STATUS";
    String K_HIS_REMARKS3 = "RELEASE AC STOP STATUS";
    String K_HIS_REMARKS4 = "RELEASE AC ONLY DEBIT STATUS";
    String K_HIS_REMARKS5 = "BAN AUTO RELEASE DEBIT";
    String K_AGT_TYPE = "141290001013";
    String K_OBJ_SYSTEM = "KHMS";
    String K_SERV_CODE = "BAT004";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    short WS_AC_STS_WORD = 0;
    char WS_CI_TYP = ' ';
    char WS_ACNO_FLG = ' ';
    String WS_ACAC = " ";
    long WS_MIN_HLD_NO = 0;
    long WS_MIN_FBID_NO = 0;
    char WS_HLD_FBID_FLG = ' ';
    char WS_ORG1_TYP = ' ';
    char WS_HLD_TYP = ' ';
    char WS_SPR_TYP = ' ';
    char WS_FBID_STS = ' ';
    char WS_AMT_HLD_W_FLG = ' ';
    String WS_Z_CCY = " ";
    char WS_Z_CCY_TYPE = ' ';
    String WS_Z_ACAC_NO = " ";
    DDZSFBID_WS_OUT_DATA WS_OUT_DATA = new DDZSFBID_WS_OUT_DATA();
    DDZSFBID_WS_OUT_DATA2 WS_OUT_DATA2 = new DDZSFBID_WS_OUT_DATA2();
    DDZSFBID_WS_OUT_DETAIL WS_OUT_DETAIL = new DDZSFBID_WS_OUT_DETAIL();
    DDZSFBID_WS_OUT_LIST WS_OUT_LIST = new DDZSFBID_WS_OUT_LIST();
    char WS_SFBID_FLAG = ' ';
    char WS_NOTICE_FLAG = ' ';
    char WS_HLD_FLG = ' ';
    char WS_CDDAT_FLG = ' ';
    char WS_HLDNO_FLG = ' ';
    char WS_FBIDNO_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICACCU CICACCU = new CICACCU();
    DDRFBID DDRFBID = new DDRFBID();
    SCCSEQ SCCSEQ = new SCCSEQ();
    DCCUCHK DCCUCHK = new DCCUCHK();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRNOSI DDRNOSI = new DDRNOSI();
    CICMAGT CICMAGT = new CICMAGT();
    SCCTPCL SCCTPCL = new SCCTPCL();
    DDCNKHMS DDCNKHMS = new DDCNKHMS();
    DDCRFBID DDCRFBID = new DDCRFBID();
    CICQACAC CICQACAC = new CICQACAC();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DCRIAMST DCRIAMST = new DCRIAMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRHLD DDRHLD = new DDRHLD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DDRHLDR DDRHLDR = new DDRHLDR();
    int WS_SFBID_CNT = 0;
    int WS_FBID_CNT = 0;
    String WS_SFBID_AC_NO = " ";
    String WS_FBID_AC_NO = " ";
    char WS_ORG_TYP = ' ';
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCSFBID DDCSFBID;
    public void MP(SCCGWA SCCGWA, DDCSFBID DDCSFBID) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSFBID = DDCSFBID;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSFBID return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BERFORE TRANSLATE");
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        R000_CHK_INPUT_PROC();
        if (pgmRtn) return;
        if (DDCSFBID.FUNC != 'I' 
            && (WS_ACNO_FLG == 'D' 
            || WS_ACNO_FLG == 'T')) {
            R000_GET_ACAC_INFO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                R000_GET_CCY_INFO();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "AFTHER TRANSLATE");
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        CEP.TRC(SCCGWA, DDCSFBID.FUNC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, WS_Z_CCY);
        CEP.TRC(SCCGWA, WS_Z_CCY_TYPE);
        if (DDCSFBID.FUNC == 'F') {
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
            B014_CI_INF_PROC();
            if (pgmRtn) return;
            R000_GEN_BAN_NO();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                B010_GET_AC_MST_INF();
                if (pgmRtn) return;
                B015_CHK_AC_STS();
                if (pgmRtn) return;
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
                B020_CHECK_FBID_STS();
                if (pgmRtn) return;
                B030_SET_FBID_STS();
                if (pgmRtn) return;
                WS_NOTICE_FLAG = 'Y';
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                B012_GET_AC_SMST_INF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                CEP.TRC(SCCGWA, "11");
                B017_CHK_AC_STS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                CEP.TRC(SCCGWA, "21");
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                CEP.TRC(SCCGWA, "31");
                B020_CHECK_FBID_STS();
                if (pgmRtn) return;
                B030_SET_FBID_STS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
                CEP.TRC(SCCGWA, "41");
                CEP.TRC(SCCGWA, TDRCMST.STSW);
            }
        } else if (DDCSFBID.FUNC == 'B') {
            WS_ORG1_TYP = DDCSFBID.ORG_TYP;
            CEP.TRC(SCCGWA, WS_ORG1_TYP);
            WS_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TBL_DDTFBID_FIRST();
            if (pgmRtn) return;
            if (WS_FBIDNO_FLG == 'Y' 
                && !DDCSFBID.KEY.REF_NO.equalsIgnoreCase(DDRFBID.KEY.REF_NO)) {
                if (DDRFBID.KEY.REF_NO == null) DDRFBID.KEY.REF_NO = "";
                JIBS_tmp_int = DDRFBID.KEY.REF_NO.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) DDRFBID.KEY.REF_NO += " ";
                if () {
                    if (DDRFBID.KEY.REF_NO.trim().length() == 0) WS_MIN_FBID_NO = 0;
                    else WS_MIN_FBID_NO = Long.parseLong(DDRFBID.KEY.REF_NO);
                    CEP.TRC(SCCGWA, WS_MIN_FBID_NO);
                } else {
                    WS_FBIDNO_FLG = 'N';
                }
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                B050_RELEASE_DD_FBID_STS();
                if (pgmRtn) return;
                WS_NOTICE_FLAG = 'Y';
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                B012_GET_AC_SMST_INF();
                if (pgmRtn) return;
                B050_RELEASE_TD_FBID_STS();
                if (pgmRtn) return;
            }
        } else if (DDCSFBID.FUNC == 'I') {
            B070_INQ_FBD_DETAIL();
            if (pgmRtn) return;
        } else if (DDCSFBID.FUNC == 'L') {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                B010_GET_AC_MST_INF();
                if (pgmRtn) return;
                B013_CHK_AC_MST_INF();
                if (pgmRtn) return;
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                B012_GET_AC_SMST_INF();
                if (pgmRtn) return;
                B017_CHK_AC_STS();
                if (pgmRtn) return;
            }
            T000_OPEN_MPAGE_OUTPUT();
            if (pgmRtn) return;
            if (DDCSFBID.AC_SEQ != 0 
                || (DDCSFBID.CCY.trim().length() > 0 
                && DDCSFBID.CCY_TYP != ' ')) {
                B090_INQ_FBD_LIST();
                if (pgmRtn) return;
            } else {
                B095_BROWSE_ALL_AC();
                if (pgmRtn) return;
            }
        } else if (DDCSFBID.FUNC == 'C') {
            B014_CI_INF_PROC();
            if (pgmRtn) return;
            if (CICACCU.DATA.CI_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PER_NOT_SET_CR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            R000_GEN_BAN_NO();
            if (pgmRtn) return;
            B001_JUDGE_ACCOUNT_TYPE();
            if (pgmRtn) return;
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TD_AC_NOT_SET_CR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                B010_GET_AC_MST_INF();
                if (pgmRtn) return;
                R000_CHECK_STS_TBL();
                if (pgmRtn) return;
                B015_CHK_AC_STS();
                if (pgmRtn) return;
                B040_SET_CR_STS();
                if (pgmRtn) return;
                WS_NOTICE_FLAG = 'Y';
            }
        } else if (DDCSFBID.FUNC == 'R') {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                B060_RELEASE_AC_CR_STS();
                if (pgmRtn) return;
                WS_NOTICE_FLAG = 'Y';
            }
        } else if (DDCSFBID.FUNC == 'A') {
            B100_CHK_FBID_STS();
            if (pgmRtn) return;
            B110_CHK_AC_STS();
            if (pgmRtn) return;
            B120_REL_FBID_STS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSFBID.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (!(DDCSFBID.FUNC == 'I' 
            || DDCSFBID.FUNC == 'L')) {
            R000_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_JUDGE_ACC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (DDCSFBID.KEY.TYPE == '1') {
            IBS.init(SCCGWA, DCCUCHK);
            DCCUCHK.DATA.OPT_TYP = '2';
            DCCUCHK.DATA.FUNC = 'P';
            if (DDCSFBID.ORG_TYP == '1') {
                DCCUCHK.DATA.SPR_TYP = '2';
            } else {
                DCCUCHK.DATA.SPR_TYP = '1';
            }
            DCCUCHK.DATA.AC = DDCSFBID.KEY.AC_NO;
            DCCUCHK.DATA.SEQ = DDCSFBID.AC_SEQ;
            S000_CALL_DCZUCHK();
            if (pgmRtn) return;
        }
        if (DDCSFBID.KEY.TYPE == '2') {
            IBS.init(SCCGWA, DCCUCHK);
            DCCUCHK.DATA.OPT_TYP = '1';
            DCCUCHK.DATA.FUNC = 'P';
            if (DDCSFBID.ORG_TYP == '1') {
                DCCUCHK.DATA.SPR_TYP = '2';
            } else {
                DCCUCHK.DATA.SPR_TYP = '1';
            }
            DCCUCHK.DATA.AC = DDCSFBID.KEY.AC_NO;
            DCCUCHK.DATA.SEQ = DDCSFBID.AC_SEQ;
            S000_CALL_DCZUCHK();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
    }
    public void B001_JUDGE_ACCOUNT_TYPE() throws IOException,SQLException,Exception {
        if ((DDCSFBID.KEY.TYPE == '1' 
            || DDCSFBID.KEY.TYPE == '2') 
            && (DDCSFBID.FUNC == 'F' 
            || DDCSFBID.FUNC == 'C')) {
            R000_JUDGE_ACC();
            if (pgmRtn) return;
        }
    }
    public void T000_CHECK_DCTIAACR_CNT() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.set = "WS-SFBID-CNT=COUNT(*)";
        DCTIAACR_RD.where = "SUB_AC = :WS_SFBID_AC_NO "
            + "AND ACCR_FLG = '1'";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        CEP.TRC(SCCGWA, WS_SFBID_CNT);
        if (WS_SFBID_CNT > 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.ENT_AC_NOT_DR_BAN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_CCY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        S000_READUP_DDTCCY();
        if (pgmRtn) return;
        WS_Z_CCY = DDRCCY.CCY;
        WS_Z_CCY_TYPE = DDRCCY.CCY_TYPE;
        CEP.TRC(SCCGWA, WS_Z_CCY);
        CEP.TRC(SCCGWA, WS_Z_CCY_TYPE);
    }
    public void B010_GET_AC_MST_INF() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSFBID.KEY.AC_NO;
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            S000_READUP_DDTMST();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DDCSFBID.KEY.AC_NO;
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            T000_READ_TBL_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_CDDAT_FLG == 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B011_GET_AC_IAMST_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        CEP.TRC(SCCGWA, "2222222222");
        DCRIAMST.KEY.VIA_AC = DDCSFBID.KEY.AC_NO;
        S000_READUP_DCTIAMST();
        if (pgmRtn) return;
    }
    public void B012_GET_AC_SMST_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_READUP_TDTSMST();
        if (pgmRtn) return;
        WS_Z_CCY = TDRSMST.CCY;
        WS_Z_CCY_TYPE = TDRSMST.CCY_TYP;
        CEP.TRC(SCCGWA, WS_Z_CCY);
        CEP.TRC(SCCGWA, WS_Z_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSFBID.ORG_TYP);
        if (DDCSFBID.FUNC == 'F') {
            CEP.TRC(SCCGWA, "--CUNDAN--");
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
                && DDCSFBID.ORG_TYP == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TD_REP_HAS_IMP_FBID);
            }
        }
    }
    public void B013_CHK_AC_MST_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B0131_CHK_AC_IAMST_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRIAMST.AC_STS);
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
        if (DCRIAMST.AC_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B014_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSFBID.KEY.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
    }
    public void R000_GEN_BAN_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "HOLD";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1).trim().length() == 0) WS_OUT_DATA.WS_REF_NO.WS_HLD_DATE = 0;
        else WS_OUT_DATA.WS_REF_NO.WS_HLD_DATE = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1));
        WS_OUT_DATA.WS_REF_NO.WS_HLD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_OUT_DATA.WS_REF_NO.WS_HLD_SYSNO = 0;
        WS_OUT_DATA.WS_REF_NO.WS_HLD_SEQ = (int) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_REF_NO);
        CEP.TRC(SCCGWA, "GEN BUSINESS NO");
    }
    public void R000_CHECK_STS_TBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP1;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP3;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DCRCDDAT.CARD_STSW + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STSW);
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            BPCFCSTS.AP_MMO = K_STS_TABLE_APP2;
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            CEP.TRC(SCCGWA, TDRSMST.STSW);
        }
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        CEP.TRC(SCCGWA, "-----CI-STSW-----");
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW.substring(0, 80));
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B015_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            CEP.TRC(SCCGWA, "222");
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            CEP.TRC(SCCGWA, "---333---");
            if (DCRCDDAT.CARD_STS == 'C') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_CLOSE);
            }
        }
    }
    public void B017_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B016_CHK_AC_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRIAMST.AC_STS);
        CEP.TRC(SCCGWA, "999");
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
        if (DCRIAMST.AC_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_FBID_STS() throws IOException,SQLException,Exception {
        T000_READ_TBL_DDTHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HLD_FLG);
        CEP.TRC(SCCGWA, DDCSFBID.ORG_TYP);
        if (WS_HLD_FLG == 'Y' 
            && DDCSFBID.ORG_TYP == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.BNK_NOT_FBID);
        }
    }
    public void B030_SET_FBID_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        B030_10_READ_DDTFBID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DDCSFBID.ORG_TYP);
            if (DDCSFBID.ORG_TYP == '1') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBID_REC_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B030_30_WRITE_DDTFBID();
        if (pgmRtn) return;
        B030_40_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B040_SET_CR_STS() throws IOException,SQLException,Exception {
        B040_20_REWRITE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        B030_30_WRITE_DDTFBID();
        if (pgmRtn) return;
    }
    public void B041_SET_CR_STS() throws IOException,SQLException,Exception {
        B040_20_REWRITE_DCTIAMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
        B030_30_WRITE_DDTFBID();
        if (pgmRtn) return;
    }
    public void B031_SET_FBID_STS() throws IOException,SQLException,Exception {
        B041_20_REWRITE_DCTIAMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
        B030_30_WRITE_DDTFBID();
        if (pgmRtn) return;
    }
    public void B032_SET_FBID_STS() throws IOException,SQLException,Exception {
        B030_10_READ_DDTFBID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.ORG_TYP);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && DDCSFBID.ORG_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBID_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B030_30_WRITE_DDTFBID();
        if (pgmRtn) return;
    }
    public void B030_10_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        if (DDCSFBID.ORG_TYP == '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "1" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
        }
        if (DDCSFBID.ORG_TYP == '2') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "1" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
        }
        S000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B040_20_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 12 - 1) + "1" + DDRCCY.STS_WORD.substring(12 + 1 - 1);
        S000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B040_20_REWRITE_DCTIAMST() throws IOException,SQLException,Exception {
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 6 - 1) + "1" + DCRIAMST.STS_WORD.substring(6 + 1 - 1);
        S000_REWRITE_DCTIAMST();
        if (pgmRtn) return;
    }
    public void B041_20_REWRITE_DCTIAMST() throws IOException,SQLException,Exception {
        if (DDCSFBID.ORG_TYP == '1') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 2 - 1) + "1" + DCRIAMST.STS_WORD.substring(2 + 1 - 1);
        }
        if (DDCSFBID.ORG_TYP == '2') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 5 - 1) + "1" + DCRIAMST.STS_WORD.substring(5 + 1 - 1);
        }
        S000_REWRITE_DCTIAMST();
        if (pgmRtn) return;
    }
    public void B042_20_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        if (DDCSFBID.ORG_TYP == '1') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "1" + TDRSMST.STSW.substring(7 + 1 - 1);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1));
        if (DDCSFBID.ORG_TYP == '2') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "1" + TDRSMST.STSW.substring(8 + 1 - 1);
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1));
        S000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B030_30_WRITE_DDTFBID() throws IOException,SQLException,Exception {
        DDCSFBID.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA.WS_REF_NO);
        CEP.TRC(SCCGWA, DDCSFBID.KEY.REF_NO);
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, "XXXXXXXXXX");
        CEP.TRC(SCCGWA, DDCSFBID.KEY.REF_NO);
        DDRFBID.KEY.REF_NO = DDCSFBID.KEY.REF_NO;
        CEP.TRC(SCCGWA, "YYYYYYYYYYY");
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        DDRFBID.TYPE = DDCSFBID.KEY.TYPE;
        if (DDCSFBID.ORG_TYP == '1') {
            DDRFBID.STS = '1';
        } else {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0") 
                    && DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("0")) {
                    DDRFBID.STS = '1';
                } else {
                    DDRFBID.STS = '3';
                }
            }
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                    && TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
                    DDRFBID.STS = '1';
                } else {
                    DDRFBID.STS = '3';
                }
            }
        }
        CEP.TRC(SCCGWA, DDRFBID.STS);
        DDRFBID.ORG_TYP = DDCSFBID.ORG_TYP;
        DDRFBID.BOOK_NO = DDCSFBID.BOOK_NO;
        DDRFBID.ORG_NAME = DDCSFBID.ORG_NAME;
        DDRFBID.ORG_TYPE = DDCSFBID.ORG_TYPE;
        DDRFBID.EFF_DATE = DDCSFBID.EFF_DATE;
        DDRFBID.EXP_DATE = DDCSFBID.EXP_DATE;
        DDRFBID.REASON = DDCSFBID.REASON;
        DDRFBID.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.CRT_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.CRT_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.SLAW_NM1 = DDCSFBID.SLAW_NM1;
        DDRFBID.SLAW_NO1 = DDCSFBID.SLAW_NO1;
        DDRFBID.SLAW_NM2 = DDCSFBID.SLAW_NM2;
        DDRFBID.SLAW_NO2 = DDCSFBID.SLAW_NO2;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.ORG_TYP);
        CEP.TRC(SCCGWA, DDRFBID.ORG_NAME);
        CEP.TRC(SCCGWA, DDRFBID.EFF_DATE);
        CEP.TRC(SCCGWA, DDRFBID.EXP_DATE);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        DDCRFBID.FUNC = 'A';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if ((DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0") 
                && DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("0")) 
                || DDCSFBID.ORG_TYP == '1') {
                B030_10_REWRITE_DDTCCY();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if ((TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
                && TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) 
                || DDCSFBID.ORG_TYP == '1') {
                B042_20_REWRITE_TDTSMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRSMST.STSW);
            }
        }
        T000_SET_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_40_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDCSFBID.KEY.REF_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDCSFBID.FUNC == 'F') {
            DDRHLDR.HLD_TYP = 'A';
            DDRHLDR.CHG_WRIT_NO = DDCSFBID.BOOK_NO;
            DDRHLDR.CHG_RSN = DDCSFBID.REASON;
            DDRHLDR.LAW_OFF_NM1 = DDCSFBID.SLAW_NM1;
            DDRHLDR.LAW_ID_NO1 = DDCSFBID.SLAW_NO1;
            DDRHLDR.LAW_OFF_NM2 = DDCSFBID.SLAW_NM2;
            DDRHLDR.LAW_ID_NO2 = DDCSFBID.SLAW_NO2;
            DDRHLDR.ORG_TYPE = DDCSFBID.ORG_TYPE;
        }
        if (DDCSFBID.FUNC == 'B') {
            DDRHLDR.HLD_TYP = 'B';
            DDRHLDR.CHG_WRIT_NO = DDCSFBID.RLS_BOOK_NO;
            DDRHLDR.CHG_RSN = DDCSFBID.RLS_REASON;
            DDRHLDR.LAW_OFF_NM1 = DDCSFBID.RLAW_NM1;
            DDRHLDR.LAW_ID_NO1 = DDCSFBID.RLAW_NO1;
            DDRHLDR.LAW_OFF_NM2 = DDCSFBID.RLAW_NM2;
            DDRHLDR.LAW_ID_NO2 = DDCSFBID.RLAW_NO2;
        }
        DDRHLDR.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRHLDR.AC);
        if (DDCSFBID.ORG_TYP == '1') {
            DDRHLDR.SPR_BR_TYP = '2';
        }
        if (DDCSFBID.ORG_TYP == '2') {
            DDRHLDR.SPR_BR_TYP = '1';
        }
        DDRHLDR.SPR_BR_NM = DDCSFBID.ORG_NAME;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B050_RELEASE_DD_FBID_STS() throws IOException,SQLException,Exception {
        B050_10_CHECK_AC_CCY_STS();
        if (pgmRtn) return;
        B050_20_CHECK_AC_FBID_REC();
        if (pgmRtn) return;
        DDCRFBID.FUNC = 'U';
        DDRFBID.STS = '2';
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLAW_NM1 = DDCSFBID.RLAW_NM1;
        DDRFBID.RLAW_NO1 = DDCSFBID.RLAW_NO1;
        DDRFBID.RLAW_NM2 = DDCSFBID.RLAW_NM2;
        DDRFBID.RLAW_NO2 = DDCSFBID.RLAW_NO2;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        B030_40_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B050_50_HLD_FBID_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AMT_HLD_W_FLG);
        while (WS_AMT_HLD_W_FLG == '1') {
            B050_60_AMTHLD_W_SECOND_PROC();
            if (pgmRtn) return;
        }
        S000_REWRITE_DDTCCY();
        if (pgmRtn) return;
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_50_HLD_FBID_PROC() throws IOException,SQLException,Exception {
        T000_READ_TBL_DDTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_HLDNO_FLG == 'Y') {
            if (DDRHLD.KEY.HLD_NO == null) DDRHLD.KEY.HLD_NO = "";
            JIBS_tmp_int = DDRHLD.KEY.HLD_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DDRHLD.KEY.HLD_NO += " ";
            if () {
                if (DDRHLD.KEY.HLD_NO.trim().length() == 0) WS_MIN_HLD_NO = 0;
                else WS_MIN_HLD_NO = Long.parseLong(DDRHLD.KEY.HLD_NO);
                WS_HLD_TYP = DDRHLD.HLD_TYP;
                WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
            } else {
                WS_HLDNO_FLG = 'N';
            }
        }
        CEP.TRC(SCCGWA, WS_MIN_HLD_NO);
        CEP.TRC(SCCGWA, WS_HLD_TYP);
        CEP.TRC(SCCGWA, WS_SPR_TYP);
        CEP.TRC(SCCGWA, WS_FBID_STS);
        if (WS_FBID_STS == '3') {
        } else {
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFFFF");
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
            CEP.TRC(SCCGWA, WS_ORG1_TYP);
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                if (WS_ORG1_TYP == '1') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
                }
                if (WS_ORG1_TYP == '2') {
                    if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                    JIBS_tmp_int = DDRCCY.STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                    DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "0" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
                }
            } else {
                if (WS_ORG1_TYP == '1') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 7 - 1) + "0" + TDRSMST.STSW.substring(7 + 1 - 1);
                }
                if (WS_ORG1_TYP == '2') {
                    if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                    JIBS_tmp_int = TDRSMST.STSW.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                    TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "0" + TDRSMST.STSW.substring(8 + 1 - 1);
                }
            }
            B050_50_01_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_50_01_PROC() throws IOException,SQLException,Exception {
        WS_HLD_FBID_FLG = ' ';
        if (WS_HLDNO_FLG == 'N' 
            && WS_FBIDNO_FLG == 'N') {
            WS_HLD_FBID_FLG = '0';
        } else {
            if (WS_MIN_HLD_NO < WS_MIN_FBID_NO) {
                CEP.TRC(SCCGWA, "======HLD=======");
                WS_HLD_FBID_FLG = '1';
                CEP.TRC(SCCGWA, DDRHLD.KEY.HLD_NO);
                CEP.TRC(SCCGWA, DDRHLD.HLD_TYP);
                CEP.TRC(SCCGWA, DDRHLD.SPR_BR_TYP);
                CEP.TRC(SCCGWA, DDRHLD.HLD_AMT);
            }
            if (WS_MIN_HLD_NO > WS_MIN_FBID_NO) {
                CEP.TRC(SCCGWA, "======FBID=======");
                WS_HLD_FBID_FLG = '2';
                CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
            }
        }
        WS_AMT_HLD_W_FLG = '0';
        CEP.TRC(SCCGWA, WS_AMT_HLD_W_FLG);
        CEP.TRC(SCCGWA, WS_HLD_FBID_FLG);
        if (WS_HLD_FBID_FLG == '1' 
            && DDRHLD.SPR_BR_TYP == '1' 
            && DDRHLD.HLD_TYP == '1') {
            CEP.TRC(SCCGWA, "=====AAAAAAAAA=====");
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 8 - 1) + "1" + DDRCCY.STS_WORD.substring(8 + 1 - 1);
            } else {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 2 - 1) + "1" + TDRSMST.STSW.substring(2 + 1 - 1);
            }
        }
        if (WS_HLD_FBID_FLG == '2') {
            CEP.TRC(SCCGWA, "=====BBBBBBBB=====");
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
                JIBS_tmp_int = DDRCCY.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
                DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 17 - 1) + "1" + DDRCCY.STS_WORD.substring(17 + 1 - 1);
            } else {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 8 - 1) + "1" + TDRSMST.STSW.substring(8 + 1 - 1);
            }
        }
        if (WS_HLD_FBID_FLG == '1' 
            && DDRHLD.SPR_BR_TYP == '1' 
            && DDRHLD.HLD_TYP == '2') {
            CEP.TRC(SCCGWA, "=====CCCCCCCCC=====");
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL + DDRHLD.HLD_AMT;
            } else {
                TDRSMST.HBAL = TDRSMST.HBAL + DDRHLD.HLD_AMT;
            }
            WS_AMT_HLD_W_FLG = '1';
        }
        if (WS_HLD_FBID_FLG == '1' 
            && DDRHLD.SPR_BR_TYP == '2' 
            && DDRHLD.HLD_TYP == '2') {
            CEP.TRC(SCCGWA, "=====DDDDDDDDDD=====");
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                DDRCCY.HOLD_BAL = DDRCCY.HOLD_BAL + DDRHLD.HLD_AMT;
            } else {
                TDRSMST.HBAL = TDRSMST.HBAL + DDRHLD.HLD_AMT;
            }
            WS_AMT_HLD_W_FLG = '1';
        }
        CEP.TRC(SCCGWA, WS_AMT_HLD_W_FLG);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD.substring(0, 17));
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRSMST.STSW.substring(0, 8));
        CEP.TRC(SCCGWA, DDRCCY.HOLD_BAL);
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        if (WS_HLD_FBID_FLG == '1') {
            IBS.init(SCCGWA, DDRHLD);
            CEP.TRC(SCCGWA, WS_MIN_HLD_NO);
            DDRHLD.KEY.HLD_NO = "" + WS_MIN_HLD_NO;
            JIBS_tmp_int = DDRHLD.KEY.HLD_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DDRHLD.KEY.HLD_NO = "0" + DDRHLD.KEY.HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            DDRHLD.HLD_STS = 'N';
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
        }
        if (WS_HLD_FBID_FLG == '2') {
            IBS.init(SCCGWA, DDRFBID);
            DDRFBID.AC = WS_ACAC;
            DDRFBID.TYPE = '1';
            DDRFBID.KEY.REF_NO = "" + WS_MIN_FBID_NO;
            JIBS_tmp_int = DDRFBID.KEY.REF_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DDRFBID.KEY.REF_NO = "0" + DDRFBID.KEY.REF_NO;
            CEP.TRC(SCCGWA, DDRFBID.AC);
            CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
            T000_READ_DDTFBID_UPD();
            if (pgmRtn) return;
            DDRFBID.STS = '1';
            DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTFBID();
            if (pgmRtn) return;
        }
    }
    public void B050_60_AMTHLD_W_SECOND_PROC() throws IOException,SQLException,Exception {
        WS_MIN_HLD_NO = 88888888888888888888;
        T000_READ_TBL_DDTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_HLDNO_FLG == 'Y') {
            if (DDRHLD.KEY.HLD_NO == null) DDRHLD.KEY.HLD_NO = "";
            JIBS_tmp_int = DDRHLD.KEY.HLD_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) DDRHLD.KEY.HLD_NO += " ";
            if () {
                if (DDRHLD.KEY.HLD_NO.trim().length() == 0) WS_MIN_HLD_NO = 0;
                else WS_MIN_HLD_NO = Long.parseLong(DDRHLD.KEY.HLD_NO);
                WS_HLD_TYP = DDRHLD.HLD_TYP;
                WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
            } else {
                WS_MIN_HLD_NO = 88888888888888888888;
            }
        }
        CEP.TRC(SCCGWA, WS_MIN_HLD_NO);
        CEP.TRC(SCCGWA, WS_HLD_TYP);
        CEP.TRC(SCCGWA, WS_SPR_TYP);
        B050_50_01_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void T000_READ_DDTFBID_UPD() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.upd = true;
        IBS.READ(SCCGWA, DDRFBID, DDTFBID_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.REWRITE(SCCGWA, DDRFBID, DDTFBID_RD);
    }
    public void T000_READ_TBL_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        CEP.TRC(SCCGWA, WS_ACAC);
        DDRHLD.AC = WS_ACAC;
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = 'W'";
        DDTHLD_RD.fst = true;
        DDTHLD_RD.order = "HLD_NO";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==DDTHLD FOUND==");
            WS_HLDNO_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==DDTHLD NOT FOUND==");
            WS_HLDNO_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TBL_DDTFBID_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        CEP.TRC(SCCGWA, WS_ACAC);
        DDRFBID.AC = WS_ACAC;
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "AC = :DDRFBID.AC "
            + "AND STS = '3'";
        DDTFBID_RD.fst = true;
        DDTFBID_RD.order = "REF_NO";
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "==DDTFBID FOUND==");
            WS_FBIDNO_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "==DDTFBID NOT FOUND==");
            WS_FBIDNO_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTFBID";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B051_RELEASE_FBID_STS() throws IOException,SQLException,Exception {
        B051_10_CHECK_AC_IAMST_STS();
        if (pgmRtn) return;
        B050_20_CHECK_AC_FBID_REC();
        if (pgmRtn) return;
        R000_CHK_BAN_CNT();
        if (pgmRtn) return;
        if (WS_FBID_CNT == 1 
            && WS_ORG_TYP == '1') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 2 - 1) + "0" + DCRIAMST.STS_WORD.substring(2 + 1 - 1);
            S000_REWRITE_DCTIAMST();
            if (pgmRtn) return;
        }
        if (WS_FBID_CNT == 1 
            && WS_ORG_TYP == '2') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 5 - 1) + "0" + DCRIAMST.STS_WORD.substring(5 + 1 - 1);
            S000_REWRITE_DCTIAMST();
            if (pgmRtn) return;
        }
        DDCRFBID.FUNC = 'U';
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DDRFBID.ORG_TYP == '2') {
            DDRFBID.RLAW_NM1 = DDCSFBID.RLAW_NM1;
            DDRFBID.RLAW_NO1 = DDCSFBID.RLAW_NO1;
            DDRFBID.RLAW_NM2 = DDCSFBID.RLAW_NM2;
            DDRFBID.RLAW_NO2 = DDCSFBID.RLAW_NO2;
        }
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_RELEASE_TD_FBID_STS() throws IOException,SQLException,Exception {
        B050_20_CHECK_AC_FBID_REC();
        if (pgmRtn) return;
        DDCRFBID.FUNC = 'U';
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLAW_NM1 = DDCSFBID.RLAW_NM1;
        DDRFBID.RLAW_NO1 = DDCSFBID.RLAW_NO1;
        DDRFBID.RLAW_NM2 = DDCSFBID.RLAW_NM2;
        DDRFBID.RLAW_NO2 = DDCSFBID.RLAW_NO2;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        B030_40_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B050_50_HLD_FBID_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AMT_HLD_W_FLG);
        while (WS_AMT_HLD_W_FLG == '1') {
            B050_60_AMTHLD_W_SECOND_PROC();
            if (pgmRtn) return;
        }
        S000_REWRITE_TDTSMST();
        if (pgmRtn) return;
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_10_CHECK_AC_MST_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSFBID.KEY.AC_NO;
        S000_READUP_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("0") 
            && DDRMST.AC_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSFBID.RLS_BR);
    }
    public void B050_10_CHECK_AC_CCY_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_READUP_DDTCCY();
        if (pgmRtn) return;
    }
    public void B050_30_CHECK_AC_TD_STS() throws IOException,SQLException,Exception {
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("0") 
            && TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSFBID.RLS_BR);
    }
    public void B051_10_CHECK_AC_IAMST_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DDCSFBID.KEY.AC_NO;
        S000_READUP_DCTIAMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && DCRIAMST.STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_STS_NOT_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSFBID.RLS_BR);
    }
    public void B050_20_CHECK_AC_FBID_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRFBID);
        IBS.init(SCCGWA, DDRFBID);
        DDCRFBID.FUNC = 'R';
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRFBID.TYPE = '1';
        DDRFBID.KEY.REF_NO = DDCSFBID.KEY.REF_NO;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        if (DDCRFBID.RETURN_INFO == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRFBID.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.CRT_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REL_BR_NOT_SET_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.STS == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FBD_CR_REC_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_FBID_STS = DDRFBID.STS;
        CEP.TRC(SCCGWA, WS_FBID_STS);
    }
    public void B060_RELEASE_AC_CR_STS() throws IOException,SQLException,Exception {
        B060_10_CHECK_AC_CCY_STS();
        if (pgmRtn) return;
        B060_20_CHECK_AC_CR_REC();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 12 - 1) + "0" + DDRCCY.STS_WORD.substring(12 + 1 - 1);
        S000_REWRITE_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        DDCRFBID.FUNC = 'U';
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRFBID);
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B061_RELEASE_AC_CR_STS() throws IOException,SQLException,Exception {
        B061_10_CHECK_AC_IAMST_STS();
        if (pgmRtn) return;
        B061_20_CHECK_AC_CR_REC();
        if (pgmRtn) return;
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 6 - 1) + "0" + DCRIAMST.STS_WORD.substring(6 + 1 - 1);
        S000_REWRITE_DCTIAMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        DDCRFBID.FUNC = 'U';
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRFBID);
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_10_READ_DDTFBID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRFBID);
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "AC = :DDRFBID.AC "
            + "AND TYPE = '1' "
            + "AND ORG_TYP = '1' "
            + "AND STS = '1'";
        DDTFBID_RD.fst = true;
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
    }
    public void B060_10_CHECK_AC_CCY_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        S000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_CR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B061_10_CHECK_AC_IAMST_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
            DCRIAMST.KEY.VIA_AC = DCCPACTY.OUTPUT.VIA_AC;
        } else {
            DCRIAMST.KEY.VIA_AC = DDCSFBID.KEY.AC_NO;
        }
        S000_READUP_DCTIAMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSFBID.KEY.AC_NO);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
        JIBS_tmp_int = DCRIAMST.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
        if (DCRIAMST.STS_WORD.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("0")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_STS_NOT_CR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_20_CHECK_AC_CR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRFBID);
        IBS.init(SCCGWA, DDRFBID);
        DDCRFBID.FUNC = 'R';
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRFBID.TYPE = '2';
        DDRFBID.KEY.REF_NO = DDCSFBID.KEY.REF_NO;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        if (DDCRFBID.RETURN_INFO == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRFBID.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.CRT_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REL_BR_NOT_SET_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.STS == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FBD_CR_REC_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B061_20_CHECK_AC_CR_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRFBID);
        IBS.init(SCCGWA, DDRFBID);
        DDCRFBID.FUNC = 'R';
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRFBID.TYPE = '2';
        DDRFBID.KEY.REF_NO = DDCSFBID.KEY.REF_NO;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        if (DDCRFBID.RETURN_INFO == 'N') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRFBID.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.CRT_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REL_BR_NOT_SET_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRFBID.STS == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FBD_CR_REC_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_INQ_FBD_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRFBID);
        IBS.init(SCCGWA, DDRFBID);
        IBS.init(SCCGWA, WS_OUT_DETAIL);
        DDCRFBID.FUNC = 'I';
        DDRFBID.KEY.REF_NO = DDCSFBID.KEY.REF_NO;
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
        T000_INQ_DETAIL_OUTPUT();
        if (pgmRtn) return;
    }
    public void B090_INQ_FBD_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCRFBID);
        IBS.init(SCCGWA, DDRFBID);
        IBS.init(SCCGWA, WS_OUT_LIST);
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRFBID.TYPE = DDCSFBID.KEY.TYPE;
        R100_START_BROWSE_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        R300_READ_NEXT_PROC();
        if (pgmRtn) return;
        while (DDCRFBID.RETURN_INFO != 'E' 
            && SCCMPAG.FUNC != 'E') {
            T000_INQ_LIST_OUTPUT();
            if (pgmRtn) return;
            R300_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        R500_END_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void B095_BROWSE_ALL_AC() throws IOException,SQLException,Exception {
        DDRCCY.CUS_AC = DDCSFBID.KEY.AC_NO;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((DDRCCY.CCY.equalsIgnoreCase(DDCSFBID.CCY) 
                || DDCSFBID.CCY.trim().length() == 0) 
                && (DDRCCY.CCY_TYPE == DDCSFBID.CCY_TYP 
                || DDCSFBID.CCY_TYP == ' ')) {
                WS_Z_ACAC_NO = DDRCCY.KEY.AC;
                WS_Z_CCY = DDRCCY.CCY;
                WS_Z_CCY_TYPE = DDRCCY.CCY_TYPE;
                CEP.TRC(SCCGWA, WS_Z_CCY);
                CEP.TRC(SCCGWA, WS_Z_CCY_TYPE);
                X000_GET_AC_SEQ();
                if (pgmRtn) return;
                B090_INQ_FBD_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        TDRSMST.AC_NO = DDCSFBID.KEY.AC_NO;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if ((TDRSMST.CCY.equalsIgnoreCase(DDCSFBID.CCY) 
                || DDCSFBID.CCY.trim().length() == 0) 
                && (TDRSMST.CCY_TYP == DDCSFBID.CCY_TYP 
                || DDCSFBID.CCY_TYP == ' ')) {
                WS_Z_ACAC_NO = TDRSMST.KEY.ACO_AC;
                WS_Z_CCY = TDRSMST.CCY;
                WS_Z_CCY_TYPE = TDRSMST.CCY_TYP;
                CEP.TRC(SCCGWA, WS_Z_CCY);
                CEP.TRC(SCCGWA, WS_Z_CCY_TYPE);
                X000_GET_AC_SEQ();
                if (pgmRtn) return;
                B090_INQ_FBD_LIST();
                if (pgmRtn) return;
            }
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
    }
    public void B100_CHK_FBID_STS() throws IOException,SQLException,Exception {
        DDRFBID.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUPD_DDTFBID();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBID_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSFBID.CHK_BR_FLG == 'N') {
        } else {
            if (DDRFBID.CRT_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REL_BR_NOT_SET_BR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDRFBID.STS == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_FBD_CR_REC_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            S000_READUP_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_FBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            S000_READUP_DDTCCY();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_STS_NOT_FBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B120_REL_FBID_STS() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 16 - 1) + "0" + DDRCCY.STS_WORD.substring(16 + 1 - 1);
            S000_REWRITE_DDTCCY();
            if (pgmRtn) return;
            WS_NOTICE_FLAG = 'Y';
        } else {
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 7 - 1) + "0" + TDRCMST.STSW.substring(7 + 1 - 1);
            S000_REWRITE_TDTSMST();
            if (pgmRtn) return;
        }
        DDRFBID.STS = '2';
        DDRFBID.RLS_REASON = DDCSFBID.RLS_REASON;
        DDRFBID.RLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.RLS_BOOK_NO = DDCSFBID.RLS_BOOK_NO;
        DDRFBID.RLS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRFBID.RLS_TL = SCCGWA.COMM_AREA.TL_ID;
        DDRFBID.RLS_AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRFBID.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRFBID.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRFBID.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPD_DDTFBID();
        if (pgmRtn) return;
        T000_RLS_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void R000_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        WS_MIN_HLD_NO = 88888888888888888888;
        WS_MIN_FBID_NO = 88888888888888888888;
        CEP.TRC(SCCGWA, WS_MIN_HLD_NO);
        CEP.TRC(SCCGWA, WS_MIN_FBID_NO);
        if (DDCSFBID.FUNC == 'F' 
            || DDCSFBID.FUNC == 'C') {
            if (DDCSFBID.KEY.TYPE == '2' 
                && DDCSFBID.ORG_TYP == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ORGAN_NOT_DEBIT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.KEY.AC_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.KEY.TYPE == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.ORG_TYP == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ORG_TYP_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.ORG_TYP == '2' 
                && DDCSFBID.BOOK_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_BOOK_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.ORG_TYPE != ' ' 
                && DDCSFBID.ORG_TYPE != '1' 
                && DDCSFBID.ORG_TYPE != '2' 
                && DDCSFBID.ORG_TYPE != '3') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ORG_TYPE_ERR);
            }
            if (DDCSFBID.EFF_DATE == 0) {
                DDCSFBID.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (DDCSFBID.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_MNOT_LT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.EXP_DATE == 0) {
                DDCSFBID.EXP_DATE = 99991231;
            }
            if (DDCSFBID.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DT_MNOT_LT_AC_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.EXP_DATE != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = DDCSFBID.EXP_DATE;
                SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
                SCSSCKDT1.MP(SCCGWA, SCCCKDT);
                CEP.TRC(SCCGWA, DDCSFBID.EXP_DATE);
                CEP.TRC(SCCGWA, SCCCKDT.RC);
                if (SCCCKDT.RC != 0) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FBD_EXP_DT_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (DDCSFBID.KEY.TYPE != '1' 
                && DDCSFBID.KEY.TYPE != '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.ORG_TYP == '2' 
                && DDCSFBID.KEY.TYPE == '1' 
                && DDCSFBID.SLAW_NM1.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAW_NM_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.ORG_TYP == '2' 
                && DDCSFBID.KEY.TYPE == '1' 
                && DDCSFBID.SLAW_NO1.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LAW_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSFBID.FUNC == 'B' 
            || DDCSFBID.FUNC == 'R') {
            if (DDCSFBID.KEY.AC_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.KEY.TYPE == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.KEY.REF_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSFBID.KEY.TYPE != '3' 
                && DDCSFBID.KEY.TYPE != '4') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSFBID.FUNC == 'L') {
            if (DDCSFBID.KEY.AC_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSFBID.FUNC != 'I') {
            if (DDCSFBID.CCY.trim().length() > 0 
                && DDCSFBID.CCY_TYP != ' ') {
                WS_ACNO_FLG = 'D';
            }
            if (DDCSFBID.AC_SEQ != 0) {
                WS_ACNO_FLG = 'T';
            }
            CEP.TRC(SCCGWA, WS_ACNO_FLG);
        }
        if (DDCSFBID.FUNC == 'I') {
            if (DDCSFBID.KEY.REF_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSFBID.FUNC == 'A') {
            if (DDCSFBID.KEY.AC_NO.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_SET_FMT_OUTPUT() throws IOException,SQLException,Exception {
        if (DDCSFBID.KEY.AC_NO.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, DDRFBID.KEY.REF_NO, WS_OUT_DATA.WS_REF_NO);
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_REF_NO);
            WS_OUT_DATA.WS_AC_NO = DDCSFBID.KEY.AC_NO;
            WS_OUT_DATA.WS_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_AC_NO);
            WS_OUT_DATA.WS_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            WS_OUT_DATA.WS_STS = DDRFBID.STS;
            WS_OUT_DATA.WS_EFF_DT = DDRFBID.EFF_DATE;
            WS_OUT_DATA.WS_EXP_DT = DDRFBID.EXP_DATE;
            WS_OUT_DATA.WS_BOOK_NO = DDRFBID.BOOK_NO;
            WS_OUT_DATA.WS_ORG_NAME = DDRFBID.ORG_NAME;
            WS_OUT_DATA.WS_REASON = DDRFBID.REASON;
            WS_OUT_DATA.WS_TR_TLR = DDRFBID.CRT_TL;
            WS_OUT_DATA.WS_SLAW_NM1 = DDCSFBID.SLAW_NM1;
            WS_OUT_DATA.WS_SLAW_NO1 = DDCSFBID.SLAW_NO1;
            WS_OUT_DATA.WS_SLAW_NM2 = DDCSFBID.SLAW_NM2;
            WS_OUT_DATA.WS_SLAW_NO2 = DDCSFBID.SLAW_NO2;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1479;
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_EFF_DT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_EXP_DT);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_RLS_FMT_OUTPUT() throws IOException,SQLException,Exception {
        WS_OUT_DATA2.WS_EFF_DT2 = DDCSFBID.EFF_DATE;
        WS_OUT_DATA2.WS_EXP_DT2 = DDCSFBID.EXP_DATE;
        if (DDCSFBID.FUNC == 'A') {
            WS_OUT_DATA2.WS_REF_NO2 = "" + WS_FBID_STS;
            JIBS_tmp_int = WS_OUT_DATA2.WS_REF_NO2.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_OUT_DATA2.WS_REF_NO2 = "0" + WS_OUT_DATA2.WS_REF_NO2;
        } else {
            WS_OUT_DATA2.WS_REF_NO2 = DDCSFBID.KEY.REF_NO;
        }
        WS_OUT_DATA2.WS_AC_NO2 = DDCSFBID.KEY.AC_NO;
        WS_OUT_DATA2.WS_AC_NAME2 = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_OUT_DATA2.WS_TR_TLR2 = SCCGWA.COMM_AREA.TL_ID;
        WS_OUT_DATA2.WS_AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        WS_OUT_DATA2.WS_RLAW_NM1 = DDCSFBID.RLAW_NM1;
        WS_OUT_DATA2.WS_RLAW_NO1 = DDCSFBID.RLAW_NO1;
        WS_OUT_DATA2.WS_RLAW_NM2 = DDCSFBID.RLAW_NM2;
        WS_OUT_DATA2.WS_RLAW_NO2 = DDCSFBID.RLAW_NO2;
        CEP.TRC(SCCGWA, DDRFBID.EFF_DATE);
        CEP.TRC(SCCGWA, DDRFBID.EXP_DATE);
        CEP.TRC(SCCGWA, DDCSFBID.EFF_DATE);
        CEP.TRC(SCCGWA, DDCSFBID.EXP_DATE);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_REF_NO2);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_AC_NO2);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_EFF_DT2);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_EFF_DT2);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_TR_TLR2);
        CEP.TRC(SCCGWA, WS_OUT_DATA2.WS_AUTH_TLR);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_OUT_DATA2;
        SCCFMT.DATA_LEN = 985;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_INQ_DETAIL_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CEP.TRC(SCCGWA, DDRFBID.AC);
        CICQACAC.DATA.ACAC_NO = DDRFBID.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        WS_OUT_DETAIL.WS_D_AC_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        WS_OUT_DETAIL.WS_D_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        WS_OUT_DETAIL.WS_D_CCY_TYP = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        WS_OUT_DETAIL.WS_D_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            R000_GET_CCY_INFO();
            if (pgmRtn) return;
            WS_OUT_DETAIL.WS_D_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            B012_GET_AC_SMST_INF();
            if (pgmRtn) return;
            WS_OUT_DETAIL.WS_D_BAL = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
        }
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_BAL);
        WS_OUT_DETAIL.WS_D_AC_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_OUT_DETAIL.WS_D_TYPE = DDRFBID.TYPE;
        WS_OUT_DETAIL.WS_D_REF_NO = DDRFBID.KEY.REF_NO;
        WS_OUT_DETAIL.WS_D_STS = DDRFBID.STS;
        WS_OUT_DETAIL.WS_D_ORG_TYP = DDRFBID.ORG_TYP;
        WS_OUT_DETAIL.WS_D_BOOK_NO = DDRFBID.BOOK_NO;
        WS_OUT_DETAIL.WS_D_ORG_NAME = DDRFBID.ORG_NAME;
        WS_OUT_DETAIL.WS_D_EFF_DT = DDRFBID.EFF_DATE;
        WS_OUT_DETAIL.WS_D_EXP_DT = DDRFBID.EXP_DATE;
        WS_OUT_DETAIL.WS_D_F_REASON = DDRFBID.REASON;
        WS_OUT_DETAIL.WS_D_CRT_TL = DDRFBID.CRT_TL;
        WS_OUT_DETAIL.WS_D_CRT_BR = DDRFBID.CRT_BR;
        WS_OUT_DETAIL.WS_D_CRT_AUTH = DDRFBID.CRT_AUTH_TL;
        WS_OUT_DETAIL.WS_D_RLS_B_NO = DDRFBID.RLS_BOOK_NO;
        WS_OUT_DETAIL.WS_D_R_REASON = DDRFBID.RLS_REASON;
        WS_OUT_DETAIL.WS_D_RLS_DT = DDRFBID.RLS_DATE;
        WS_OUT_DETAIL.WS_D_RLS_TL = DDRFBID.RLS_TL;
        WS_OUT_DETAIL.WS_D_RLS_BR = DDRFBID.RLS_BR;
        WS_OUT_DETAIL.WS_D_RLS_AUTH = DDRFBID.RLS_AUTH_TL;
        WS_OUT_DETAIL.WS_D_SLAW_NM1 = DDRFBID.SLAW_NM1;
        WS_OUT_DETAIL.WS_D_SLAW_NO1 = DDRFBID.SLAW_NO1;
        WS_OUT_DETAIL.WS_D_SLAW_NM2 = DDRFBID.SLAW_NM2;
        WS_OUT_DETAIL.WS_D_SLAW_NO2 = DDRFBID.SLAW_NO2;
        WS_OUT_DETAIL.WS_D_RLAW_NM1 = DDRFBID.RLAW_NM1;
        WS_OUT_DETAIL.WS_D_RLAW_NO1 = DDRFBID.RLAW_NO1;
        WS_OUT_DETAIL.WS_D_RLAW_NM2 = DDRFBID.RLAW_NM2;
        WS_OUT_DETAIL.WS_D_RLAW_NO2 = DDRFBID.RLAW_NO2;
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_EFF_DT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_EXP_DT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_RLS_DT);
        CEP.TRC(SCCGWA, DDRFBID.STS);
        CEP.TRC(SCCGWA, DDRFBID.REASON);
        CEP.TRC(SCCGWA, DDRFBID.RLS_REASON);
        CEP.TRC(SCCGWA, DDRFBID.SLAW_NM1);
        CEP.TRC(SCCGWA, DDRFBID.SLAW_NO1);
        CEP.TRC(SCCGWA, DDRFBID.SLAW_NM2);
        CEP.TRC(SCCGWA, DDRFBID.SLAW_NO2);
        CEP.TRC(SCCGWA, DDRFBID.RLAW_NM1);
        CEP.TRC(SCCGWA, DDRFBID.RLAW_NO1);
        CEP.TRC(SCCGWA, DDRFBID.RLAW_NM2);
        CEP.TRC(SCCGWA, DDRFBID.RLAW_NO2);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_SLAW_NM1);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_SLAW_NO1);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_SLAW_NM2);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_SLAW_NO2);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_RLAW_NM1);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_RLAW_NO1);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_RLAW_NM2);
        CEP.TRC(SCCGWA, WS_OUT_DETAIL.WS_D_RLAW_NO2);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DETAIL;
        SCCFMT.DATA_LEN = 2436;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_INQ_LIST_OUTPUT() throws IOException,SQLException,Exception {
        WS_OUT_LIST.WS_LIST_NO = DDRFBID.AC;
        WS_OUT_LIST.WS_LIST_NAME = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        WS_OUT_LIST.WS_LIST_TYPE = DDRFBID.TYPE;
        WS_OUT_LIST.WS_L_REF_NO = DDRFBID.KEY.REF_NO;
        WS_OUT_LIST.WS_L_EFF_DT = DDRFBID.EFF_DATE;
        WS_OUT_LIST.WS_L_EXP_DT = DDRFBID.EXP_DATE;
        WS_OUT_LIST.WS_L_RLS_DT = DDRFBID.RLS_DATE;
        WS_OUT_LIST.WS_L_BOOK_NO = DDRFBID.BOOK_NO;
        WS_OUT_LIST.WS_L_ORG_NAME = DDRFBID.ORG_NAME;
        WS_OUT_LIST.WS_L_REASON = DDRFBID.REASON;
        WS_OUT_LIST.WS_L_CRT_BR = DDRFBID.CRT_BR;
        WS_OUT_LIST.WS_L_STS = DDRFBID.STS;
        WS_OUT_LIST.WS_L_ORG_TYP = DDRFBID.ORG_TYP;
        WS_OUT_LIST.WS_L_CCY = WS_Z_CCY;
        WS_OUT_LIST.WS_L_CCY_TYPE = WS_Z_CCY_TYPE;
        WS_OUT_LIST.WS_L_AGR_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_LIST_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        CEP.TRC(SCCGWA, DDRFBID.KEY.REF_NO);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_EFF_DT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_EXP_DT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_RLS_DT);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_BOOK_NO);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_ORG_NAME);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_REASON);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_CRT_BR);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_STS);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_ORG_TYP);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_CCY);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_CCY_TYPE);
        CEP.TRC(SCCGWA, WS_OUT_LIST.WS_L_AGR_SEQ);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_LIST);
        SCCMPAG.DATA_LEN = 845;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_OPEN_MPAGE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 845;
        if ("24".trim().length() == 0) SCCMPAG.SCR_ROW_CNT = 0;
        else SCCMPAG.SCR_ROW_CNT = Short.parseShort("24");
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R100_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'K';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void R300_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'R';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void R500_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        DDCRFBID.FUNC = 'B';
        DDCRFBID.OPT = 'E';
        DDCRFBID.REC_PTR = DDRFBID;
        DDCRFBID.REC_LEN = 2201;
        S000_CALL_DDZRFBID();
        if (pgmRtn) return;
    }
    public void R000_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        if ((DDCSFBID.FUNC == 'B' 
            || DDCSFBID.FUNC == 'I' 
            || DDCSFBID.FUNC == 'R' 
            || DDCSFBID.FUNC == 'A') 
            && (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            CEP.TRC(SCCGWA, "ACAC");
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = DDCSFBID.KEY.AC_NO;
        } else {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = DDCSFBID.KEY.AC_NO;
            CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        }
        CICQACAC.DATA.AGR_SEQ = DDCSFBID.AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DDCSFBID.CCY;
        CICQACAC.DATA.CR_FLG = DDCSFBID.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void R000_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (DDCSFBID.FUNC == 'F') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS1;
        }
        if (DDCSFBID.FUNC == 'C') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS2;
        }
        if (DDCSFBID.FUNC == 'B' 
            || DDCSFBID.FUNC == 'A') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS3;
        }
        if (DDCSFBID.FUNC == 'R') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS4;
        }
        BPCPNHIS.INFO.REF_NO = DDRFBID.KEY.REF_NO;
        if (DDCSFBID.FUNC == 'B' 
            || DDCSFBID.FUNC == 'A' 
            || DDCSFBID.FUNC == 'R') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCPNHIS.INFO.TX_TOOL = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        if (DDCSFBID.FUNC == 'F') {
            BPCPNHIS.INFO.TX_TYP_CD = "P145";
        }
        if (DDCSFBID.FUNC == 'B') {
            BPCPNHIS.INFO.TX_TYP_CD = "P146";
        }
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.CCY_TYPE);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHK_BAN_CNT() throws IOException,SQLException,Exception {
        WS_FBID_AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        WS_ORG_TYP = DDRFBID.ORG_TYP;
        CEP.TRC(SCCGWA, WS_FBID_AC_NO);
        CEP.TRC(SCCGWA, WS_ORG_TYP);
        CEP.TRC(SCCGWA, DDRFBID.TYPE);
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.set = "WS-FBID-CNT=COUNT(*)";
        DDTFBID_RD.where = "AC = :WS_FBID_AC_NO "
            + "AND ORG_TYP = :WS_ORG_TYP "
            + "AND TYPE = '1' "
            + "AND STS = '1'";
        IBS.GROUP(SCCGWA, DDRFBID, this, DDTFBID_RD);
        CEP.TRC(SCCGWA, WS_FBID_CNT);
    }
    public void R000_NOTICE_KHMS_SYS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMAGT);
        CICMAGT.DATA.ENTY_NO = DDRFBID.AC;
        CICMAGT.DATA.AGT_TYP = K_AGT_TYPE;
        CICMAGT.DATA.AGT_STS = 'N';
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
        if (CICMAGT.DATA.AGT_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "AAA");
            IBS.init(SCCGWA, DDRNOSI);
            DDRNOSI.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRNOSI.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDRNOSI.KEY.JRN_SEQ = GWA_BP_AREA.NFHIS_CUR_SEQ;
            CEP.TRC(SCCGWA, DDRNOSI.KEY.JRN_SEQ);
            DDRNOSI.VA_AC = DDCSFBID.KEY.AC_NO;
            DDRNOSI.AC_NO = DDCSFBID.KEY.AC_NO;
            DDRNOSI.TX_CODE = SCCGWA.COMM_AREA.SERV_CODE;
            DDRNOSI.TX_STS = '0';
            DDRNOSI.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
            DDRNOSI.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
            DDRNOSI.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDRNOSI.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRNOSI.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRNOSI.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
            if (DDCSFBID.FUNC == 'F' 
                || DDCSFBID.FUNC == 'C') {
                DDRNOSI.REMARK = DDCSFBID.REASON;
                if (DDCSFBID.FUNC == 'F') {
                    DDRNOSI.TX_TYP = '4';
                } else {
                    DDRNOSI.TX_TYP = '6';
                }
            } else {
                DDRNOSI.REMARK = DDCSFBID.RLS_REASON;
                if (DDCSFBID.FUNC == 'R') {
                    DDRNOSI.TX_TYP = '7';
                } else {
                    DDRNOSI.TX_TYP = '5';
                }
            }
            DDRNOSI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRNOSI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRNOSI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRNOSI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                DDRNOSI.SEND_FLG = 'Y';
            } else {
                DDRNOSI.SEND_FLG = 'N';
            }
            T000_WRITE_DDTNOSI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "1111");
            IBS.init(SCCGWA, DDCNKHMS);
            DDCNKHMS.VA_NO = DDRNOSI.VA_AC;
            DDCNKHMS.AC_NO = DDRNOSI.AC_NO;
            DDCNKHMS.TX_CODE = SCCGWA.COMM_AREA.SERV_CODE;
            DDCNKHMS.TX_STS = '0';
            DDCNKHMS.TX_TYP = DDRNOSI.TX_TYP;
            DDCNKHMS.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            DDCNKHMS.JRN_SEQ = DDRNOSI.KEY.JRN_SEQ;
            DDCNKHMS.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
            DDCNKHMS.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
            DDCNKHMS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCNKHMS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDCNKHMS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDCNKHMS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDCNKHMS.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCNKHMS.REMARK = DDRNOSI.REMARK;
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM;
            SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE;
            SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 802;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, DDCNKHMS);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
            IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
            CEP.TRC(SCCGWA, "22222");
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                S000_CALL_SCZTPCL();
                if (pgmRtn) return;
            }
            if (DCCUCHK.DATA.REL_DB_FLG == 'Y') {
                CEP.TRC(SCCGWA, "AAA");
                IBS.init(SCCGWA, DDRNOSI);
                DDRNOSI.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRNOSI.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
                GWA_BP_AREA.NFHIS_CUR_SEQ = (short) (GWA_BP_AREA.NFHIS_CUR_SEQ + 1);
                DDRNOSI.KEY.JRN_SEQ = GWA_BP_AREA.NFHIS_CUR_SEQ;
                CEP.TRC(SCCGWA, DDRNOSI.KEY.JRN_SEQ);
                DDRNOSI.VA_AC = DDRFBID.AC;
                DDRNOSI.AC_NO = DDRFBID.AC;
                DDRNOSI.TX_CODE = SCCGWA.COMM_AREA.SERV_CODE;
                DDRNOSI.TX_STS = '0';
                DDRNOSI.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
                DDRNOSI.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
                DDRNOSI.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
                DDRNOSI.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                DDRNOSI.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRNOSI.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
                DDRNOSI.REMARK = K_HIS_REMARKS5;
                DDRNOSI.TX_TYP = '7';
                DDRNOSI.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRNOSI.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRNOSI.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRNOSI.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    DDRNOSI.SEND_FLG = 'Y';
                } else {
                    DDRNOSI.SEND_FLG = 'N';
                }
                T000_WRITE_DDTNOSI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "1111");
                IBS.init(SCCGWA, DDCNKHMS);
                DDCNKHMS.VA_NO = DDRNOSI.VA_AC;
                DDCNKHMS.AC_NO = DDRNOSI.AC_NO;
                DDCNKHMS.TX_CODE = SCCGWA.COMM_AREA.SERV_CODE;
                DDCNKHMS.TX_STS = '0';
                DDCNKHMS.TX_TYP = DDRNOSI.TX_TYP;
                DDCNKHMS.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
                DDCNKHMS.JRN_SEQ = DDRNOSI.KEY.JRN_SEQ;
                DDCNKHMS.OT_JRN_NO = GWA_SC_AREA.REQ_SYS_JRN;
                DDCNKHMS.OT_DATE = GWA_SC_AREA.REQ_SYS_DATE;
                DDCNKHMS.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDCNKHMS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
                DDCNKHMS.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                DDCNKHMS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDCNKHMS.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
                DDCNKHMS.REMARK = DDRNOSI.REMARK;
                IBS.init(SCCGWA, SCCTPCL);
                SCCTPCL.SERV_AREA.OBJ_SYSTEM = K_OBJ_SYSTEM;
                SCCTPCL.SERV_AREA.SERV_CODE = K_SERV_CODE;
                SCCTPCL.SERV_AREA.SERV_TYPE = ' ';
                SCCTPCL.INP_AREA.SERV_DATA_LEN = 802;
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, DDCNKHMS);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_SEND);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BH_FILE_ECIF);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_AC_CHG_INF);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0002);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PASSWORD_CHK);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_SMS_INFO);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CHG_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_SG0001);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ACGL_GL0001);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0001);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SN_SMS_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0002);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.CC_CHANGE_CARD);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.SC_SCF_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_ECIF_AC0003);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_IBIL_NOTICE);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BROADCAST);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CASH_CHG);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_BVMS_INQ);
                IBS.CPY2CLS(SCCGWA, SCCTPCL.INP_AREA.SERV_DATA, SCCTPCL.INP_AREA.BD_CSIF_NOTICE);
                CEP.TRC(SCCGWA, "22222");
                if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
                    && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
                    S000_CALL_SCZTPCL();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void T000_READ_TBL_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_STS = 'N' "
            + "AND SPR_BR_TYP = '2' "
            + "AND HLD_TYP = '1'";
        DDTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "--DDTHLD FOUND--");
            WS_HLD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "--DDTHLD NOT FOUND--");
            WS_HLD_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TBL_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CDDAT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CDDAT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_READUP_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "STS_WORD";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, DCRIAMST.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY2_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "CUS_AC,AC_STS,AC_STS_WORD, LAST_DATE,LAST_TLR,UPDTBL_DATE,UPDTBL_TLR";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDRMST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
    }
    public void S000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDRCCY.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
    }
    public void S000_REWRITE_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        IBS.REWRITE(SCCGWA, DCRIAMST, DCTIAMST_RD);
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
    }
    public void T000_READUPD_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        DDTFBID_RD.where = "AC = :DDRFBID.AC "
            + "AND TYPE = '1' "
            + "AND ORG_TYP = '1' "
            + "AND STS = '1'";
        DDTFBID_RD.upd = true;
        IBS.READ(SCCGWA, DDRFBID, this, DDTFBID_RD);
    }
    public void T000_UPD_DDTFBID() throws IOException,SQLException,Exception {
        DDTFBID_RD = new DBParm();
        DDTFBID_RD.TableName = "DDTFBID";
        IBS.REWRITE(SCCGWA, DDRFBID, DDTFBID_RD);
        CEP.TRC(SCCGWA, DCRIAMST.STS_WORD);
    }
    public void S000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, TDRSMST.STSW);
    }
    public void T000_WRITE_DDTNOSI() throws IOException,SQLException,Exception {
        DDTNOSI_RD = new DBParm();
        DDTNOSI_RD.TableName = "DDTNOSI";
        IBS.WRITE(SCCGWA, DDRNOSI, DDTNOSI_RD);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "CUS_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.eqWhere = "AC_NO";
        IBS.STARTBR(SCCGWA, TDRSMST, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_DDZRFBID() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SRC-DDZRFBID", DDCRFBID);
        if (DDCRFBID.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCRFBID.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCHK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-CHK", DCCUCHK);
    }
    public void S000_CALL_DCZIQHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZIQHLD", DCCIQHLD);
        if (DCCIQHLD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCIQHLD.RC);
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "++++++++++++++++");
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, "====================");
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT, true);
        if (CICMAGT.RC.RC_CODE != 0) {
            if (CICMAGT.RETURN_INFO == 'N') {
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void X000_GET_AC_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CEP.TRC(SCCGWA, WS_Z_ACAC_NO);
        CICQACAC.DATA.ACAC_NO = WS_Z_ACAC_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
