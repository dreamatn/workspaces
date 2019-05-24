package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUFHSA {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_INQ_FHISA = "BP-R-INQ-FHISA";
    int K_MAX_OUTPUT_CNT = 366;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_INFO = " ";
    BPZUFHSA_WS_TEMP_BAL WS_TEMP_BAL = new BPZUFHSA_WS_TEMP_BAL();
    BPZUFHSA_WS_TEMP_CNT WS_TEMP_CNT = new BPZUFHSA_WS_TEMP_CNT();
    BPZUFHSA_WS_TEMP_AC_DT WS_TEMP_AC_DT = new BPZUFHSA_WS_TEMP_AC_DT();
    short WS_REC_CNT = 0;
    int WS_BROW_AC_DT = 0;
    BPZUFHSA_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZUFHSA_WS_OUTPUT_DATA();
    char WS_STOP_FLG = ' ';
    char WS_NEXT_STOP_FLG = ' ';
    char WS_TRANS_END_FLG = ' ';
    char WS_FHISA_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHISA BPRFHISA = new BPRFHISA();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUFHSA BPCUFHSA;
    public void MP(SCCGWA SCCGWA, BPCUFHSA BPCUFHSA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUFHSA = BPCUFHSA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUFHSA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUFHSA.RC);
        WS_STOP_FLG = 'N';
        WS_FHISA_FND_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_CAL_BROW_DT();
        if (pgmRtn) return;
        B050_BROWSE_HISA();
        if (pgmRtn) return;
        if (BPCUFHSA.DATA.FUNC_FLG == 'Y') {
            B070_TRANS_DATAS_EACH();
            if (pgmRtn) return;
        } else {
            B090_TRANS_DATAS_UNEACH();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUFHSA.DATA.STR_AC_DT);
        CEP.TRC(SCCGWA, BPCUFHSA.DATA.END_AC_DT);
        if (BPCUFHSA.DATA.FUNC_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!(BPCUFHSA.DATA.FUNC_FLG == 'Y' 
            || BPCUFHSA.DATA.FUNC_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUFHSA.DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_NOT_EXIST, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUFHSA.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUFHSA.DATA.STR_AC_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_START_DT_MUST_INPUT, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUFHSA.DATA.END_AC_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_DT_MUST_INPUT, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUFHSA.DATA.STR_AC_DT > BPCUFHSA.DATA.END_AC_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_GT_END, BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CAL_BROW_DT() throws IOException,SQLException,Exception {
        R000_GET_NEXT_AC_DT_CN();
        if (pgmRtn) return;
        WS_BROW_AC_DT = BPCOCLWD.DATE2;
    }
    public void B050_BROWSE_HISA() throws IOException,SQLException,Exception {
        WS_TRANS_END_FLG = 'Y';
        R000_STARTBR_BPTFHISA();
        if (pgmRtn) return;
        R000_READNEXT_BPTFHISA();
        if (pgmRtn) return;
        WS_REC_CNT = 1;
        while (WS_FHISA_FND_FLG != 'N' 
            && WS_STOP_FLG != 'Y') {
            CEP.TRC(SCCGWA, BPRFHISA.AC_DT);
            CEP.TRC(SCCGWA, WS_REC_CNT);
            CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
            WS_TRANS_END_FLG = 'N';
            WS_OUTPUT_DATA.WS_TS_DATA[WS_REC_CNT-1].WS_TS_AC_DT = BPRFHISA.AC_DT;
            WS_OUTPUT_DATA.WS_TS_DATA[WS_REC_CNT-1].WS_TS_AC_BAL = BPRFHISA.CUR_BAL;
            WS_REC_CNT += 1;
            if (BPRFHISA.AC_DT < BPCUFHSA.DATA.STR_AC_DT) {
                WS_NEXT_STOP_FLG = 'Y';
            }
            if (WS_NEXT_STOP_FLG == 'Y') {
                WS_STOP_FLG = 'Y';
            }
            R000_READNEXT_BPTFHISA();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "END READNEXT");
        R000_ENDBR_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_REC_CNT);
        if (WS_TRANS_END_FLG == 'N') {
            WS_TEMP_AC_DT.WS_FIRST_BAL_DT = WS_OUTPUT_DATA.WS_TS_DATA[WS_REC_CNT - 1-1].WS_TS_AC_DT;
        }
        CEP.TRC(SCCGWA, WS_TEMP_AC_DT.WS_FIRST_BAL_DT);
    }
    public void B070_TRANS_DATAS_EACH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFHSA.DATA.OUTPUT_DATA);
        WS_TEMP_CNT.WS_T = 1;
        WS_TEMP_CNT.WS_THIS_COUNT = 1;
        WS_TEMP_CNT.WS_LAST_COUNT = 2;
        WS_TEMP_AC_DT.WS_FLOAT_AC_DT = BPCUFHSA.DATA.END_AC_DT;
        CEP.TRC(SCCGWA, WS_TEMP_AC_DT.WS_FLOAT_AC_DT);
        R000_CAL_DAYS();
        if (pgmRtn) return;
        while (WS_TEMP_CNT.WS_T <= WS_OUTPUT_DATA.WS_REC_NUM 
            && WS_TRANS_END_FLG != 'Y') {
            CEP.TRC(SCCGWA, WS_TEMP_CNT.WS_T);
            CEP.TRC(SCCGWA, WS_TEMP_CNT.WS_THIS_COUNT);
            CEP.TRC(SCCGWA, WS_TEMP_CNT.WS_LAST_COUNT);
            CEP.TRC(SCCGWA, WS_TEMP_AC_DT.WS_FLOAT_AC_DT);
            WS_TEMP_BAL.WS_THIS_BAL = WS_OUTPUT_DATA.WS_TS_DATA[WS_TEMP_CNT.WS_THIS_COUNT-1].WS_TS_AC_BAL;
            WS_TEMP_AC_DT.WS_THIS_AC_DT = WS_OUTPUT_DATA.WS_TS_DATA[WS_TEMP_CNT.WS_THIS_COUNT-1].WS_TS_AC_DT;
            WS_TEMP_BAL.WS_LAST_BAL = WS_OUTPUT_DATA.WS_TS_DATA[WS_TEMP_CNT.WS_LAST_COUNT-1].WS_TS_AC_BAL;
            WS_TEMP_AC_DT.WS_LAST_AC_DT = WS_OUTPUT_DATA.WS_TS_DATA[WS_TEMP_CNT.WS_LAST_COUNT-1].WS_TS_AC_DT;
            BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_TEMP_CNT.WS_T-1].TS_AC_DT = WS_TEMP_AC_DT.WS_FLOAT_AC_DT;
            if (WS_TEMP_AC_DT.WS_FLOAT_AC_DT >= WS_TEMP_AC_DT.WS_THIS_AC_DT) {
                BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_TEMP_CNT.WS_T-1].TS_AC_BAL = WS_TEMP_BAL.WS_THIS_BAL;
            } else {
                BPCUFHSA.DATA.OUTPUT_DATA.TS_DATA[WS_TEMP_CNT.WS_T-1].TS_AC_BAL = WS_TEMP_BAL.WS_LAST_BAL;
            }
            R000_GET_LAST_AC_DT_CN();
            if (pgmRtn) return;
            WS_TEMP_AC_DT.WS_FLOAT_AC_DT = BPCOCLWD.DATE2;
            if (WS_TEMP_AC_DT.WS_FLOAT_AC_DT <= WS_TEMP_AC_DT.WS_LAST_AC_DT) {
                WS_TEMP_CNT.WS_THIS_COUNT += 1;
                WS_TEMP_CNT.WS_LAST_COUNT += 1;
            }
            if (WS_TEMP_AC_DT.WS_FLOAT_AC_DT < WS_TEMP_AC_DT.WS_FIRST_BAL_DT) {
                WS_TRANS_END_FLG = 'Y';
                CEP.TRC(SCCGWA, "NO_MORE_REC_ABOUT_BAL");
            }
            WS_TEMP_CNT.WS_T += 1;
        }
        WS_OUTPUT_DATA.WS_REC_NUM = (short) (WS_TEMP_CNT.WS_T - 1);
        CEP.TRC(SCCGWA, WS_TEMP_CNT.WS_T);
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_REC_NUM);
        CEP.TRC(SCCGWA, WS_TEMP_CNT.WS_LAST_COUNT);
        BPCUFHSA.DATA.OUTPUT_DATA.REC_NUM = 0;
        BPCUFHSA.DATA.OUTPUT_DATA.REC_NUM = WS_OUTPUT_DATA.WS_REC_NUM;
        CEP.TRC(SCCGWA, BPCUFHSA.DATA.OUTPUT_DATA.REC_NUM);
    }
    public void B090_TRANS_DATAS_UNEACH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFHSA.DATA.OUTPUT_DATA);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFHSA.DATA.OUTPUT_DATA);
    }
    public void R000_CAL_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = BPCUFHSA.DATA.STR_AC_DT;
        BPCOCLWD.DATE2 = BPCUFHSA.DATA.END_AC_DT;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.WDAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.HDAYS);
        WS_OUTPUT_DATA.WS_REC_NUM = (short) BPCOCLWD.DAYS;
        CEP.TRC(SCCGWA, WS_OUTPUT_DATA.WS_REC_NUM);
    }
    public void R000_GET_LAST_AC_DT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = WS_TEMP_AC_DT.WS_FLOAT_AC_DT;
        BPCOCLWD.DAYS = -2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void R000_GET_NEXT_AC_DT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = BPCUFHSA.DATA.END_AC_DT;
        BPCOCLWD.DAYS = 2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCBJPRM: BPZPCLWD ERROR ");
            CEP.TRC(SCCGWA, BPCOCLWD.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_STARTBR_BPTFHISA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHSA);
        IBS.init(SCCGWA, BPRFHISA);
        BPCIFHSA.DATA.FUNC = '1';
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        BPCIFHSA.DATA.AC = BPCUFHSA.DATA.AC;
        BPCIFHSA.DATA.CCY = BPCUFHSA.DATA.CCY;
        BPCIFHSA.DATA.AC_DT = WS_BROW_AC_DT;
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.AC);
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.CCY);
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.AC_DT);
        BPCIFHSA.DATA.REC_LEN = 144;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
    }
    public void R000_READNEXT_BPTFHISA() throws IOException,SQLException,Exception {
        BPCIFHSA.DATA.FUNC = '2';
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        BPCIFHSA.DATA.REC_LEN = 144;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_FHISA_FND_FLG = 'N';
        } else {
            WS_FHISA_FND_FLG = 'F';
        }
    }
    public void R000_ENDBR_BPTFHISA() throws IOException,SQLException,Exception {
        BPCIFHSA.DATA.FUNC = '3';
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        BPCIFHSA.DATA.REC_LEN = 144;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZIFHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_INQ_FHISA, BPCIFHSA);
        CEP.TRC(SCCGWA, BPCIFHSA.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
        JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[2].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHSA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUFHSA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "NORMAL");
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUFHSA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUFHSA = ");
            CEP.TRC(SCCGWA, BPCUFHSA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
