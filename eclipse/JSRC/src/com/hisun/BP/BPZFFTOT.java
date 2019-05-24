package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFTOT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFFTOT";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_GET_FEE_HIST = "BP-INQ-FHIS";
    String WS_TEMP_RECORD = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_TOT_AMT = 0;
    short WS_TOT_CNT = 0;
    short WS_CNT1 = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    BPZFFTOT_WS_TMP WS_TMP = new BPZFFTOT_WS_TMP();
    BPZFFTOT_WS_RC WS_RC = new BPZFFTOT_WS_RC();
    short WS_LOC = 0;
    char WS_FHIS_FLAG = ' ';
    char WS_REC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCFPARM BPCFPARM = new BPCFPARM();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPVFSTD BPVFSTD = new BPVFSTD();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRFFHIS BPRFMHIS = new BPRFFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCGWA SCCGWA;
    BPCFFTOT BPCFFTOT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRFHIST BPRFHIS;
    public void MP(SCCGWA SCCGWA, BPCFFTOT BPCFFTOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFTOT = BPCFFTOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFFTOT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCFFTOT.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFTOT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CHECK_DATE();
        if (pgmRtn) return;
        B020_CAL_TOT_AMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFFTOT.DATA.SUR_CYC != '0' 
            && BPCFFTOT.DATA.SUR_CYC != '1' 
            && BPCFFTOT.DATA.SUR_CYC != '2' 
            && BPCFFTOT.DATA.SUR_CYC != '3') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_CYC_ERR, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFFTOT.DATA.CLASSFY_TYPE != '0' 
            && BPCFFTOT.DATA.CLASSFY_TYPE != '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_TYPE_ERR, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFFTOT.DATA.AMT_SUR != '0' 
            && BPCFFTOT.DATA.AMT_SUR != '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_SOURCE_ERR, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFFTOT.DATA.MST_SUR != '0' 
            && BPCFFTOT.DATA.MST_SUR != '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFFTOT.DATA.MST_SUR == '0' 
            && BPCFFTOT.DATA.AC_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFFTOT.DATA.MST_SUR == '1' 
            && BPCFFTOT.DATA.CI_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_MUST_INPUT, BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_DATE() throws IOException,SQLException,Exception {
        WS_END_DATE = SCCGWA.COMM_AREA.TR_DATE;
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.TR_DATE;
        if (BPCFFTOT.DATA.SUR_CYC == '0' 
            || BPCFFTOT.DATA.SUR_CYC == '1') {
            if (BPCFFTOT.DATA.SUR_CYC == '0') {
                WS_TMP.WS_TMP_MM = (short) (BPCFFTOT.DATA.CYC_CNT * 12);
            } else {
                WS_TMP.WS_TMP_MM = (short) (BPCFFTOT.DATA.CYC_CNT * 1);
            }
            SCCCLDT.MTHS = (short) (WS_TMP.WS_TMP_MM * -1);
        } else {
            if (BPCFFTOT.DATA.SUR_CYC == '2') {
                WS_TMP.WS_TMP_DAYS = (short) (BPCFFTOT.DATA.CYC_CNT * 7);
            } else {
                WS_TMP.WS_TMP_DAYS = (short) (BPCFFTOT.DATA.CYC_CNT * 1);
            }
            SCCCLDT.DAYS = WS_TMP.WS_TMP_DAYS;
            SCCCLDT.DAYS = WS_TMP.WS_TMP_DAYS * -1;
        }
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_RC.WS_AP = SCCGWA.COMM_AREA.AP_MMO;
            WS_RC.WS_RETURN = SCCCLDT.RC;
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_STR_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
    }
    public void B020_CAL_TOT_AMT() throws IOException,SQLException,Exception {
        WS_FHIS_FLAG = 'Y';
        S000_CALL_BPZIFHIS_STARTBR();
        if (pgmRtn) return;
        S000_CALL_BPZIFHIS_READNEXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_FHIS_FLAG);
        CEP.TRC(SCCGWA, BPCFFTOT.DATA.CLASSFY_TYPE);
        CEP.TRC(SCCGWA, BPCFFTOT.DATA.MST_SUR);
        CEP.TRC(SCCGWA, BPCFFTOT.DATA.AC_NO);
        while (WS_FHIS_FLAG != 'N') {
            CEP.TRC(SCCGWA, WS_FHIS_FLAG);
            CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
            BPRFHIS = (BPRFHISA) BPCIFHIS.INPUT.REC_PT;
            WS_REC_FLAG = 'N';
            if (BPCFFTOT.DATA.MST_SUR == '0') {
                if (BPRFHIS.KEY.AC.equalsIgnoreCase(BPCFFTOT.DATA.AC_NO)) {
                    WS_REC_FLAG = 'Y';
                }
            } else {
                if (BPRFHIS.CI_NO.equalsIgnoreCase(BPCFFTOT.DATA.CI_NO)) {
                    WS_REC_FLAG = 'Y';
                }
            }
            CEP.TRC(SCCGWA, WS_REC_FLAG);
            if (WS_REC_FLAG == 'Y') {
                CEP.TRC(SCCGWA, BPRFMHIS.VAL.TX_AMT);
                CEP.TRC(SCCGWA, BPRFMHIS.VAL.ADJ_AMT);
                if (BPCFFTOT.DATA.CLASSFY_TYPE == '0') {
                    if (BPCFFTOT.DATA.AMT_SUR == '0') {
                        WS_TOT_AMT += BPRFMHIS.VAL.TX_AMT;
                    } else {
                        WS_TOT_AMT += BPRFMHIS.VAL.ADJ_AMT;
                    }
                } else {
                    if (BPCFFTOT.DATA.AMT_SUR == '0') {
                        WS_TOT_CNT += BPRFMHIS.VAL.TX_CNT;
                    } else {
                        WS_TOT_CNT += 1;
                    }
                }
            }
            S000_CALL_BPZIFHIS_READNEXT();
            if (pgmRtn) return;
        }
        S000_CALL_BPZIFHIS_ENDBR();
        if (pgmRtn) return;
        BPCFFTOT.DATA.TOT_AMT = WS_TOT_AMT;
        BPCFFTOT.DATA.TOT_CNT = WS_TOT_CNT;
        CEP.TRC(SCCGWA, BPCFFTOT.DATA.TOT_AMT);
        CEP.TRC(SCCGWA, BPCFFTOT.DATA.TOT_CNT);
    }
    public void S000_CALL_BPZIFHIS_STARTBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHIS);
        BPCIFHIS.INPUT.REF_NO = "FEE HISTORY";
        BPCIFHIS.INPUT.STR_AC_DT = WS_STR_DATE;
        BPCIFHIS.INPUT.END_AC_DT = WS_END_DATE;
        BPCIFHIS.INPUT.FUNC = '1';
        BPCIFHIS.INPUT.REC_LEN = 690;
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        CEP.TRC(SCCGWA, "CHECKLEN");
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REC_LEN);
        IBS.CALLCPN(SCCGWA, CPN_GET_FEE_HIST, BPCIFHIS);
        if (BPCIFHIS.OUTPUT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIFHIS_READNEXT() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '2';
        IBS.CALLCPN(SCCGWA, CPN_GET_FEE_HIST, BPCIFHIS);
        if (BPCIFHIS.OUTPUT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
                WS_FHIS_FLAG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFFTOT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZIFHIS_ENDBR() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '3';
        IBS.CALLCPN(SCCGWA, CPN_GET_FEE_HIST, BPCIFHIS);
        if (BPCIFHIS.OUTPUT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFFTOT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFTOT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFTOT = ");
            CEP.TRC(SCCGWA, BPCFFTOT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
