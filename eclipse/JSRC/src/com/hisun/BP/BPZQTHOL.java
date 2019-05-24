package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQTHOL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZQTHOL";
    String CPN_R_MAINTAIN_THOL = "BP-R-MAINTAIN-THOL";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    int WS_DATE = 0;
    short WS_TMP_DATE = 0;
    int WS_I = 0;
    int WS_K = 0;
    int WS_J = 0;
    String WS_HOL_DATA = " ";
    BPZQTHOL_REDEFINES8 REDEFINES8 = new BPZQTHOL_REDEFINES8();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRTHOL BPRTHOL = new BPRTHOL();
    BPCRTHOL BPCRTHOL = new BPCRTHOL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCQTHOL BPCQTHOL;
    public void MP(SCCGWA SCCGWA, BPCQTHOL BPCQTHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQTHOL = BPCQTHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQTHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCQTHOL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQTHOL.INPUT_DAT.CNTY_CODE);
        CEP.TRC(SCCGWA, BPCQTHOL.INPUT_DAT.CITY_CODE);
        CEP.TRC(SCCGWA, BPCQTHOL.INPUT_DAT.EFF_DATE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_HOLI_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQTHOL.INPUT_DAT.CNTY_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT, BPCQTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCIQCNT);
            BPCIQCNT.INPUT_DAT.CNTY_CD = BPCQTHOL.INPUT_DAT.CNTY_CODE;
            S000_CALL_BPZIQCNT();
            if (pgmRtn) return;
            if (BPCIQCNT.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_INVALID, BPCQTHOL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCQTHOL.INPUT_DAT.CITY_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCIQCIT);
            BPCIQCIT.INPUT_DAT.CITY_CD = BPCQTHOL.INPUT_DAT.CITY_CODE;
            BPCIQCIT.INPUT_DAT.CNTY_CD = BPCQTHOL.INPUT_DAT.CNTY_CODE;
            S000_CALL_BPZIQCIT();
            if (pgmRtn) return;
            if (BPCIQCIT.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CITY_CD_NOTFND, BPCQTHOL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCQTHOL.INPUT_DAT.EFF_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DT_M_INPUT, BPCQTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCQTHOL.INPUT_DAT.EFF_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID, BPCQTHOL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_HOLI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        BPRTHOL.KEY.EFF_DATE = BPCQTHOL.INPUT_DAT.EFF_DATE;
        WS_DATE = BPCQTHOL.INPUT_DAT.EFF_DATE;
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        CEP.TRC(SCCGWA, BPRTHOL.REDEFINES7.HOL_TXT_LEN1);
        CEP.TRC(SCCGWA, BPCRTHOL.DATA_LEN);
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'Q';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CITY_HOL_NOTFND, BPCQTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_I = 0;
        WS_K = 0;
        WS_J = 0;
        WS_HOL_DATA = BPRTHOL.REDEFINES7.HOL_TXT_TEXT1;
        IBS.CPY2CLS(SCCGWA, WS_HOL_DATA, REDEFINES8);
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            if (REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_OPT != 'D') {
                if (REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_CNT > 1) {
                    for (WS_J = 1; WS_J <= REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_CNT; WS_J += 1) {
                        IBS.init(SCCGWA, SCCCLDT);
                        R000_COMPUTE_DATE();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_K = WS_K + 1;
                    CEP.TRC(SCCGWA, WS_K);
                    BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_DT;
                    CEP.TRC(SCCGWA, BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT);
                    BPCQTHOL.HOL_DATA[WS_K-1].HOL_NAME = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_NAME;
                    BPCQTHOL.HOL_DATA[WS_K-1].HOL_TPY = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_TPY;
                }
            }
        }
    }
    public void R000_COMPUTE_DATE() throws IOException,SQLException,Exception {
        WS_K = WS_K + 1;
        CEP.TRC(SCCGWA, WS_K);
        if (WS_J == 1) {
            BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_DT;
            WS_TMP_DATE = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_DT;
            CEP.TRC(SCCGWA, BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT);
            BPCQTHOL.HOL_DATA[WS_K-1].HOL_NAME = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_NAME;
            BPCQTHOL.HOL_DATA[WS_K-1].HOL_TPY = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_TPY;
        } else {
            JIBS_tmp_str[0] = "" + WS_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + SCCCLDT.DATE1;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[1].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
            JIBS_NumStr = JIBS_tmp_str[0].substring(0, 4) + JIBS_NumStr.substring(4);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
            JIBS_tmp_str[0] = "" + SCCCLDT.DATE1;
            JIBS_f0 = "";
            for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + SCCCLDT.DATE1;
            JIBS_tmp_str[1] = "" + WS_TMP_DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + JIBS_tmp_str[1] + JIBS_NumStr.substring(5 + 4 - 1);
            SCCCLDT.DATE1 = Integer.parseInt(JIBS_NumStr);
            SCCCLDT.DAYS = 1;
            JIBS_tmp_str[9] = "SCSSCLDT";
            Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
            Object obj = clazz.newInstance();
            Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCCLDT.getClass()});
            m.invoke(obj, SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, SCCCLDT.RC);
            if (SCCCLDT.RC != 0) {
                BPCQTHOL.RC.RC_CODE = SCCCLDT.RC;
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).trim().length() == 0) BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT = 0;
            else BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1));
            JIBS_tmp_str[0] = "" + SCCCLDT.DATE2;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1).trim().length() == 0) WS_TMP_DATE = 0;
            else WS_TMP_DATE = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1));
            BPCQTHOL.HOL_DATA[WS_K-1].HOL_NAME = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_NAME;
            BPCQTHOL.HOL_DATA[WS_K-1].HOL_TPY = REDEFINES8.WS_HOL_DATA1[WS_I-1].WS_HOL_TPY;
            CEP.TRC(SCCGWA, BPCQTHOL.HOL_DATA[WS_K-1].HOL_DT);
        }
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIQCNT);
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S000_CALL_BPZRTHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINTAIN_THOL, BPCRTHOL);
        if (BPCRTHOL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTHOL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQTHOL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQTHOL = ");
            CEP.TRC(SCCGWA, BPCQTHOL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
