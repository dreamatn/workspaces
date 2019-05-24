package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSCMZR;
import com.hisun.BP.BPRMSG;
import com.hisun.BP.BPRPRMT;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZMCRE {
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT_ADD_SIGLE = "CIX01";
    String K_OUTPUT_FMT_QUERY = "CIX02";
    String K_OUTPUT_FMT_MODIFY = "CIX03";
    String K_OUTPUT_FMT_DELETE = "CIX04";
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    int K_ARR_MAX_CNT = 5;
    String WS_SER_STS = " ";
    char WS_CORE_RES = ' ';
    char WS_CORE_RES1 = ' ';
    char WS_CORE_RES2 = ' ';
    char WS_CORE_RES3 = ' ';
    char WS_CORE_RES5 = ' ';
    char WS_PERI_RES = ' ';
    String WS_SIGN_AC = " ";
    String WS_CARD_NO_L = " ";
    String WS_CARD_NO_H = " ";
    String WS_CI_NO = " ";
    char WS_OPEN_SMS_AGAIN = ' ';
    int WS_SEQ = 0;
    char WS_CITCREL_FLG = ' ';
    char WS_CITCOMP_FLG = ' ';
    char WS_CITBAS_FLG = ' ';
    char WS_CITACR_FLG = ' ';
    char WS_CITAGT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRCREL CIRCREL = new CIRCREL();
    CIRCOMP CIRCOMP = new CIRCOMP();
    CIRCOMP CIRCOMPO = new CIRCOMP();
    CIRCOMP CIRCOMP0 = new CIRCOMP();
    CIRCREL CIRCREL1 = new CIRCREL();
    CIRCREL CIRCREL0 = new CIRCREL();
    CIRCREL CIRCRELO = new CIRCREL();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CICOMCRE CICOMCRE = new CICOMCRE();
    CICOCREA CICOCREA = new CICOCREA();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CIRAGT CIRAGT = new CIRAGT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCSCALL DDCSCALL = new DDCSCALL();
    BPCSCMZR BPCSCMZR = new BPCSCMZR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRMSG BPRMSG = new BPRMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMCRE CICMCRE;
    public void MP(SCCGWA SCCGWA, CICMCRE CICMCRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMCRE = CICMCRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMCRE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCRE.FUNC);
        if (CICMCRE.FUNC == 'S') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B011_MAINTAIN_SIGLE();
            if (pgmRtn) return;
        } else if (CICMCRE.FUNC == 'C') {
            B024_CANCLE_RELATION_ALL();
            if (pgmRtn) return;
        } else if (CICMCRE.FUNC == 'Q') {
            B020_QUERY_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRCOMP);
        CEP.TRC(SCCGWA, CICMCRE.FUNC);
        CEP.TRC(SCCGWA, CICMCRE.REL_OPER);
        CEP.TRC(SCCGWA, CICMCRE.AGT_NO);
        CEP.TRC(SCCGWA, CICMCRE.CI_NO);
        CEP.TRC(SCCGWA, CICMCRE.TEL_NO);
        if (CICMCRE.AGT_NO.trim().length() > 0) {
            CIRCOMP.KEY.AGT_NO = CICMCRE.AGT_NO;
            T000_READ_CITCOMP_AGT_NO();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRCOMP, CIRCOMP0);
            IBS.CLONE(SCCGWA, CIRCOMP, CIRCOMPO);
            if (WS_CITCOMP_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_SER_STS = CIRCOMP.SER_STS;
                if (CIRCOMP.EFF_FLG != 'Y') {
                    if (CICMCRE.REL_OPER == '1') {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GFX_AGT_DISABLE, CICMCRE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GFX_AGT_NOT_EFF, CICMCRE.RC);
                    }
                }
                if (CICMCRE.CI_NO.trim().length() > 0) {
                    if (!CICMCRE.CI_NO.equalsIgnoreCase(CIRCOMP.CI_NO)) {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_CI_INF_NOT_MATCH, CICMCRE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (CICMCRE.CI_NO.trim().length() > 0) {
            CIRCOMP.CI_NO = CICMCRE.CI_NO;
            T000_READ_CITCOMP_CI_NO();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRCOMP, CIRCOMP0);
            IBS.CLONE(SCCGWA, CIRCOMP, CIRCOMPO);
            if (WS_CITCOMP_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_SER_STS = CIRCOMP.SER_STS;
                if (CIRCOMP.EFF_FLG != 'Y') {
                    if (CICMCRE.REL_OPER == '1') {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GFX_AGT_DISABLE, CICMCRE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    } else {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GFX_AGT_NOT_EFF, CICMCRE.RC);
                    }
                }
                if (CICMCRE.AGT_NO.trim().length() > 0) {
                    if (!CIRCOMP.KEY.AGT_NO.equalsIgnoreCase(CICMCRE.AGT_NO)) {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_CI_INF_NOT_MATCH, CICMCRE.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
            CICMCRE.AGT_NO = CIRCOMP.KEY.AGT_NO;
        }
        IBS.init(SCCGWA, DCCUCINF);
        CEP.TRC(SCCGWA, CICMCRE.CARD_NO);
        DCCUCINF.CARD_NO = CICMCRE.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        if (DCCUCINF.CARD_STS == '0' 
            || DCCUCINF.CARD_STS == '1' 
            || DCCUCINF.CARD_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CARD_STS_ABNORMAL, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_LNK_TYP);
        if (DCCUCINF.CARD_LNK_TYP != '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LNK_CARD_NOT_CREL, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.JOIN_CUS_FLG);
        if (DCCUCINF.JOIN_CUS_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_JOIN_CARD_NOT_CREL, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CARD_STS_WORD_FAIL, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRCREL.KEY.AGT_NO = CICMCRE.AGT_NO;
        CIRCREL.KEY.CARD_NO = CICMCRE.CARD_NO;
        T000_READ_CITCREL_AGT_CARD_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCREL, CIRCREL0);
        IBS.CLONE(SCCGWA, CIRCREL, CIRCRELO);
        if (CICMCRE.REL_OPER == '1') {
            if (WS_CITCREL_FLG == 'Y') {
                if (CIRCREL.PREF_SER4 == '3' 
                    || CIRCREL.PREF_SER4 == ' ' 
                    || CIRCREL.PREF_SER4 == '2') {
                    WS_OPEN_SMS_AGAIN = 'Y';
                } else {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CRAD_ALREADY_REALIED, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        } else if (CICMCRE.REL_OPER == '2'
            || CICMCRE.REL_OPER == '3') {
            if (WS_CITCREL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CLONE(SCCGWA, CIRCREL, CIRCREL1);
            }
        } else {
        }
        if (WS_CITCREL_FLG == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS.substring(0, 1).equalsIgnoreCase("1") 
                || CIRCREL.STATUS.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                if (CICMCRE.REL_OPER == '1') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PRE_EFF_NO_APP_AGAIN, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CIRCREL.STATUS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                if (CICMCRE.REL_OPER == '2') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_PRE_DISABLE_NO_CANCL, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (CIRCREL.PREF_SER1 == '3' 
                || CIRCREL.PREF_SER2 == '3' 
                || CIRCREL.PREF_SER3 == '3' 
                || CIRCREL.PREF_SER4 == '3' 
                || CIRCREL.PREF_SER5 == '3' 
                || CIRCREL.PREF_SER6 == '3' 
                || CIRCREL.PREF_SER7 == '3' 
                || CIRCREL.PREF_SER8 == '3' 
                || CIRCREL.PREF_SER9 == '3' 
                || CIRCREL.PREF_SER4 == '5' 
                || CIRCREL.PREF_SER4 == '6') {
                if (CICMCRE.REL_OPER == '2') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CANCEL_PRE_NOT_ALLOW, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (CIRCREL.PREF_SER1 == '4' 
                || CIRCREL.PREF_SER2 == '4' 
                || CIRCREL.PREF_SER3 == '4' 
                || CIRCREL.PREF_SER4 == '4' 
                || CIRCREL.PREF_SER5 == '4' 
                || CIRCREL.PREF_SER6 == '4' 
                || CIRCREL.PREF_SER7 == '4' 
                || CIRCREL.PREF_SER8 == '4' 
                || CIRCREL.PREF_SER9 == '4' 
                || CIRCREL.PREF_SER4 == '7' 
                || CIRCREL.PREF_SER4 == '8') {
                if (CICMCRE.REL_OPER == '1') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADD_PRE_NOT_ALLOW, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B011_MAINTAIN_SIGLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMCRE.REL_OPER);
        if (CICMCRE.REL_OPER == '1') {
            B021_ADD_RELATION();
            if (pgmRtn) return;
        } else if (CICMCRE.REL_OPER == '2') {
            B022_CANCLE_RELATION();
            if (pgmRtn) return;
        } else if (CICMCRE.REL_OPER == '3') {
            B023_DELETE_RELATION();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B020_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRCOMP);
        CEP.TRC(SCCGWA, CICMCRE.CI_NO);
        CEP.TRC(SCCGWA, CICMCRE.AGT_NO);
        if (CICMCRE.AGT_NO.trim().length() == 0) {
            CIRCOMP.CI_NO = CICMCRE.CI_NO;
            T000_READ_CITCOMP_CI_NO();
            if (pgmRtn) return;
            if (WS_CITCOMP_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
            }
            CICMCRE.AGT_NO = CIRCOMP.KEY.AGT_NO;
            WS_CI_NO = CIRCOMP.CI_NO;
        } else {
            if (CICMCRE.CI_NO.trim().length() == 0) {
                CIRCOMP.KEY.AGT_NO = CICMCRE.AGT_NO;
                T000_READ_CITCOMP_AGT_NO();
                if (pgmRtn) return;
                if (WS_CITCOMP_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                CICMCRE.CI_NO = CIRCOMP.CI_NO;
                WS_CI_NO = CIRCOMP.CI_NO;
            }
        }
        CEP.TRC(SCCGWA, CICMCRE.AGT_NO);
        CIRCREL.KEY.AGT_NO = CICMCRE.AGT_NO;
        if (CICMCRE.CARD_NO.trim().length() == 0) {
            T000_STARTBR_CITCREL_AGT_NO();
            if (pgmRtn) return;
            T000_READNEXT_CITCREL();
            if (pgmRtn) return;
            if (WS_CITCREL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_COMP_NOT_RELATE_CARD, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CIRCREL.KEY.CARD_NO = CICMCRE.CARD_NO;
            T000_STARTBR_CITCREL_AGT_CARD();
            if (pgmRtn) return;
            T000_READNEXT_CITCREL();
            if (pgmRtn) return;
            if (WS_CITCREL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CARD_NOT_BELONG_GFX, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        R090_01_OUT_TITLE();
        if (pgmRtn) return;
        while (WS_CITCREL_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_TRANS_QUERY_DATA_OUTPUT();
            if (pgmRtn) return;
            R090_OUTPUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITCREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCREL();
        if (pgmRtn) return;
    }
    public void B021_ADD_RELATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        CEP.TRC(SCCGWA, CICMCRE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CIRCREL.KEY.AGT_NO = CICMCRE.AGT_NO;
        CIRCREL.KEY.CARD_NO = CICMCRE.CARD_NO;
        CEP.TRC(SCCGWA, CICMCRE.TEL_NO);
        CIRCREL.TEL_NO = CICMCRE.TEL_NO;
        R000_GET_PREF_INFOR_ADD();
        if (pgmRtn) return;
        CIRCREL.FDK = CICMCRE.FLDK;
        CIRCREL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCREL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCREL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCREL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCREL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCREL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_OPEN_SMS_AGAIN != 'Y') {
            T000_WRITE_CITCREL();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITCREL();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CIRCOMPO.CI_NO;
        BPCPNHIS.INFO.TX_TOOL = CICMCRE.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = "CI CREL ADD INFO";
        BPCPNHIS.INFO.FMT_ID = "CIRCREL";
        BPCPNHIS.INFO.FMT_ID_LEN = 381;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRCRELO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRCREL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B022_CANCLE_RELATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        R000_GET_PREF_INFOR_CANCLE();
        if (pgmRtn) return;
        CIRCREL1.STATUS = CIRCREL.STATUS;
        CIRCREL1.REL_RES = CIRCREL.REL_RES;
        CIRCREL1.PREF_SER1 = CIRCREL.PREF_SER1;
        CIRCREL1.PREF_RES1 = CIRCREL.PREF_RES1;
        CIRCREL1.PREF_SER2 = CIRCREL.PREF_SER2;
        CIRCREL1.PREF_RES2 = CIRCREL.PREF_RES2;
        CIRCREL1.PREF_SER3 = CIRCREL.PREF_SER3;
        CIRCREL1.PREF_RES3 = CIRCREL.PREF_RES3;
        CIRCREL1.PREF_SER4 = CIRCREL.PREF_SER4;
        CIRCREL1.PREF_RES4 = CIRCREL.PREF_RES4;
        CIRCREL1.PREF_SER5 = CIRCREL.PREF_SER5;
        CIRCREL1.PREF_RES5 = CIRCREL.PREF_RES5;
        CIRCREL1.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCREL1.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCREL1.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CLONE(SCCGWA, CIRCREL1, CIRCREL);
        T000_REWRITE_CITCREL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CIRCOMPO.CI_NO;
        BPCPNHIS.INFO.TX_TOOL = CICMCRE.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = "CI CREL MOD INFO";
        BPCPNHIS.INFO.FMT_ID = "CIRCREL";
        BPCPNHIS.INFO.FMT_ID_LEN = 381;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRCRELO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRCREL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        R000_TRANS_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B023_DELETE_RELATION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        CIRCREL.KEY.AGT_NO = CICMCRE.AGT_NO;
        CIRCREL.KEY.CARD_NO = CICMCRE.CARD_NO;
        T000_READ_CITCREL_AGT_CARD_UPD();
        if (pgmRtn) return;
        if (WS_CITCREL_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_RECORD_EXIST, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CITCREL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CIRCOMPO.CI_NO;
        BPCPNHIS.INFO.TX_TOOL = CICMCRE.CARD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = "CI CREL DEL INFO";
        BPCPNHIS.INFO.FMT_ID = "CIRCREL";
        BPCPNHIS.INFO.FMT_ID_LEN = 381;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRCRELO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRCREL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B024_CANCLE_RELATION_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCREL);
        IBS.init(SCCGWA, CIRCOMP);
        CIRCOMP.CI_NO = CICMCRE.CI_NO;
        T000_READ_CITCOMP_CI_NO();
        if (pgmRtn) return;
        if (WS_CITCOMP_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_GFX_AGT_CI, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_SER_STS = CIRCOMP.SER_STS;
        CIRCREL.KEY.AGT_NO = CIRCOMP.KEY.AGT_NO;
        T000_STARTBR_CITCREL_AGT_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITCREL();
        if (pgmRtn) return;
        while (WS_CITCREL_FLG != 'N') {
            IBS.CLONE(SCCGWA, CIRCREL, CIRCRELO);
            CEP.TRC(SCCGWA, CIRCREL.KEY.CARD_NO);
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            if (CIRCREL.STATUS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CIRCREL.STATUS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "TEST01");
                T000_READNEXT_CITCREL();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CIRCREL.KEY.CARD_NO);
            }
            if (CIRCREL.PREF_SER4 == '5' 
                || CIRCREL.PREF_SER4 == '6') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CANCEL_PRE_NOT_ALLOW, CICMCRE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            R000_GET_PREF_INFOR_CANCLE_ALL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRCREL.KEY.CARD_NO);
            if (WS_CITCREL_FLG == 'Y') {
                WS_SEQ += 1;
                T000_REWRITE_CITCREL();
                if (pgmRtn) return;
                if (WS_SEQ > 9999) {
                } else {
                    IBS.init(SCCGWA, BPCPNHIS);
                    BPCPNHIS.INFO.TX_TYP = 'M';
                    BPCPNHIS.INFO.DATA_FLG = 'Y';
                    BPCPNHIS.INFO.CI_NO = CIRCOMP.CI_NO;
                    BPCPNHIS.INFO.TX_TOOL = CIRCREL.KEY.CARD_NO;
                    BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
                    BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                    BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
                    BPCPNHIS.INFO.TX_RMK = "CI CREL DEL INFO";
                    BPCPNHIS.INFO.FMT_ID = "CIRCREL";
                    BPCPNHIS.INFO.FMT_ID_LEN = 381;
                    BPCPNHIS.INFO.OLD_DAT_PT = CIRCRELO;
                    BPCPNHIS.INFO.NEW_DAT_PT = CIRCREL;
                    S000_CALL_BPZPNHIS();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITCREL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCREL();
        if (pgmRtn) return;
    }
    public void R000_GET_PREF_INFOR_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICMCRE.CARD_NO;
        T000_READ_CITACR_AGR_NO();
        if (pgmRtn) return;
        if (CIRACR.STS == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_IS_CLOSED, CICMCRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_CI_NO = CIRACR.CI_NO;
        CEP.TRC(SCCGWA, WS_SER_STS);
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        if (WS_SER_STS.substring(0, 1).equalsIgnoreCase("1")) {
            CIRCREL.PREF_SER1 = '1';
            WS_CORE_RES1 = 'Y';
        } else {
            WS_CORE_RES1 = 'Y';
        }
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER1);
        CEP.TRC(SCCGWA, WS_CORE_RES1);
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        if (WS_SER_STS.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CIRCREL.PREF_SER2 = '1';
            WS_CORE_RES2 = 'Y';
        } else {
            WS_CORE_RES2 = 'Y';
        }
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER2);
        CEP.TRC(SCCGWA, WS_CORE_RES2);
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        CEP.TRC(SCCGWA, WS_SER_STS.substring(3 - 1, 3 + 1 - 1));
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        if (WS_SER_STS.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, WS_OPEN_SMS_AGAIN);
            if (WS_OPEN_SMS_AGAIN == 'Y') {
                CIRCREL.PREF_SER3 = '1';
                WS_CORE_RES3 = 'Y';
            } else {
                IBS.init(SCCGWA, DCCPACTY);
                DCCPACTY.INPUT.FUNC = '2';
                DCCPACTY.INPUT.AC = CICMCRE.CARD_NO;
                S000_CALL_DCZPACTY();
                if (pgmRtn) return;
                if (DCCPACTY.RC.RC_CODE != 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (!DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("DC")) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_GFX_PERSON_MUST_CARD, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (DCCPACTY.OUTPUT.STD_AC.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMCRE.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, CIRAGT);
                    CIRAGT.KEY.ENTY_NO = DCCPACTY.OUTPUT.STD_AC;
                    CIRAGT.KEY.ENTY_TYP = '1';
                    CIRAGT.AGT_TYP = "141200002011";
                    CIRAGT.AGT_STS = 'N';
                    T000_READ_CITAGT_FIRST();
                    if (pgmRtn) return;
                    if (WS_CITAGT_FLG == 'Y') {
                        CEP.TRC(SCCGWA, "WS-CITAGT-FND");
                        CIRCREL.PREF_SER3 = '1';
                        WS_CORE_RES3 = 'Y';
                    } else {
                        IBS.init(SCCGWA, DDCSCALL);
                        DDCSCALL.FUNC = 'A';
                        DDCSCALL.CARD_NO = CICMCRE.CARD_NO;
                        DDCSCALL.CCY = "156";
                        DDCSCALL.INT_AC = DCCPACTY.OUTPUT.STD_AC;
                        S000_CALL_DDZSCALL();
                        if (pgmRtn) return;
                        CIRCREL.PREF_SER3 = '1';
                        WS_CORE_RES3 = 'Y';
                    }
                }
            }
        } else {
            WS_CORE_RES3 = 'Y';
        }
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER3);
        CEP.TRC(SCCGWA, WS_CORE_RES3);
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        if (WS_SER_STS.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || WS_OPEN_SMS_AGAIN == 'Y') {
            CIRCREL.PREF_SER4 = '5';
            WS_PERI_RES = 'W';
        } else {
            WS_PERI_RES = 'N';
        }
        if (WS_SER_STS == null) WS_SER_STS = "";
        JIBS_tmp_int = WS_SER_STS.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_SER_STS += " ";
        if (WS_SER_STS.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CIRCREL.PREF_SER5 = '1';
            WS_CORE_RES5 = 'Y';
        } else {
            WS_CORE_RES5 = 'Y';
        }
        CEP.TRC(SCCGWA, CIRCREL.PREF_SER5);
        CEP.TRC(SCCGWA, WS_CORE_RES5);
        if (WS_CORE_RES1 == 'Y' 
            && WS_CORE_RES2 == 'Y' 
            && WS_CORE_RES3 == 'Y' 
            && WS_CORE_RES5 == 'Y') {
            WS_CORE_RES = 'Y';
        } else {
            WS_CORE_RES = 'N';
        }
        CEP.TRC(SCCGWA, WS_CORE_RES);
        CIRCREL.STATUS = "00000000000000000000000000000000";
        if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "10000000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "01000000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'N') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000010000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000000100000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00001000000000" + CIRCREL.STATUS.substring(14);
        } else {
        }
        CEP.TRC(SCCGWA, CIRCREL.STATUS);
    }
    public void R000_GET_PREF_INFOR_CANCLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICMCRE.CARD_NO;
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        T000_READ_CITACR_AGR_NO();
        if (pgmRtn) return;
        if (CIRCREL1.PREF_SER1 == '1') {
            CIRCREL.PREF_SER1 = '2';
            WS_CORE_RES1 = 'Y';
        } else {
            WS_CORE_RES1 = 'Y';
        }
        if (CIRCREL1.PREF_SER2 == '1') {
            CIRCREL.PREF_SER2 = '2';
            WS_CORE_RES2 = 'Y';
        } else {
            WS_CORE_RES2 = 'Y';
        }
        if (CIRCREL1.PREF_SER3 == '1' 
            || CIRCREL1.PREF_SER3 == '5') {
            CIRCREL.PREF_SER3 = '2';
            WS_CORE_RES3 = 'Y';
        } else {
            WS_CORE_RES3 = 'Y';
        }
        if (CIRCREL1.PREF_SER4 == '1') {
            CIRCREL.PREF_SER4 = '7';
            WS_PERI_RES = 'W';
        } else {
            WS_PERI_RES = 'Y';
        }
        if (CIRCREL1.PREF_SER5 == '1') {
            CIRCREL.PREF_SER5 = '2';
            WS_CORE_RES5 = 'Y';
        } else {
            WS_CORE_RES5 = 'Y';
        }
        if (WS_CORE_RES1 == 'Y' 
            && WS_CORE_RES2 == 'Y' 
            && WS_CORE_RES3 == 'Y' 
            && WS_CORE_RES5 == 'Y') {
            WS_CORE_RES = 'Y';
        } else {
            WS_CORE_RES = 'N';
        }
        CIRCREL.STATUS = "00000000000000000000000000000000";
        if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00100000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00010000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'N') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000001000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000000010000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000100000000" + CIRCREL.STATUS.substring(14);
        } else {
        }
    }
    public void R000_GET_PREF_INFOR_CANCLE_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CIRCREL.KEY.CARD_NO;
        CEP.TRC(SCCGWA, "AAAAAAAA");
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        T000_READ_CITACR_AGR_NO();
        if (pgmRtn) return;
        if (CIRCREL.PREF_SER1 == '1') {
            CIRCREL.PREF_SER1 = '2';
            WS_CORE_RES1 = 'Y';
        } else {
            WS_CORE_RES1 = 'Y';
        }
        if (CIRCREL.PREF_SER2 == '1') {
            CIRCREL.PREF_SER2 = '2';
            WS_CORE_RES2 = 'Y';
        } else {
            WS_CORE_RES2 = 'Y';
        }
        if (CIRCREL.PREF_SER3 == '1') {
            CIRCREL.PREF_SER3 = '2';
            WS_CORE_RES3 = 'Y';
        } else {
            WS_CORE_RES3 = 'Y';
        }
        if (CIRCREL.PREF_SER4 == '1') {
            CIRCREL.PREF_SER4 = '7';
            WS_PERI_RES = 'W';
        } else {
            WS_PERI_RES = 'Y';
        }
        if (CIRCREL.PREF_SER5 == '1') {
            CIRCREL.PREF_SER5 = '2';
            WS_CORE_RES5 = 'Y';
        } else {
            WS_CORE_RES5 = 'Y';
        }
        if (WS_CORE_RES1 == 'Y' 
            && WS_CORE_RES2 == 'Y' 
            && WS_CORE_RES3 == 'Y' 
            && WS_CORE_RES5 == 'Y') {
            WS_CORE_RES = 'Y';
        } else {
            WS_CORE_RES = 'N';
        }
        CIRCREL.STATUS = "00000000000000000000000000000000";
        if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00100000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00010000000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'Y' 
                && WS_PERI_RES == 'N') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000001000000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'Y') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000000010000" + CIRCREL.STATUS.substring(14);
        } else if (WS_CORE_RES == 'N' 
                && WS_PERI_RES == 'W') {
            if (CIRCREL.STATUS == null) CIRCREL.STATUS = "";
            JIBS_tmp_int = CIRCREL.STATUS.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) CIRCREL.STATUS += " ";
            CIRCREL.STATUS = "00000100000000" + CIRCREL.STATUS.substring(14);
        } else {
        }
    }
    public void R000_TRANS_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        if ((CICMCRE.FUNC == 'S' 
                && (CICMCRE.REL_OPER == '1' 
                || CICMCRE.REL_OPER == '2'))) {
            SCCFMT.FMTID = K_OUTPUT_FMT_ADD_SIGLE;
            SCCFMT.DATA_PTR = CICOCREA;
            SCCFMT.DATA_LEN = 807;
            CEP.TRC(SCCGWA, CICOCREA);
        } else {
        }
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R090_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R090_OUTPUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOMCRE);
        SCCMPAG.DATA_LEN = 861;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCREA);
        CICOCREA.AGT_NO = CICMCRE.AGT_NO;
        CICOCREA.CI_CNM = CICMCRE.CI_CNM;
        CICOCREA.ID_CTYP = CICMCRE.ID_CTYP;
        CICOCREA.ID_CNO = CICMCRE.ID_CNO;
        CICOCREA.REL_OPER = CICMCRE.REL_OPER;
        CICOCREA.CARD_NO = CICMCRE.CARD_NO;
        CICOCREA.CI_PNM = CICMCRE.CI_PNM;
        CICOCREA.ID_PTYP = CICMCRE.ID_PTYP;
        CICOCREA.ID_PNO = CICMCRE.ID_PNO;
        CICOCREA.TEL_NO = CICMCRE.TEL_NO;
        CICOCREA.FLDK = CICMCRE.FLDK;
    }
    public void R000_TRANS_QUERY_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CICOMCRE);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS_CI_NO();
        if (pgmRtn) return;
        CICOMCRE.CI_NO = CIRBAS.KEY.CI_NO;
