package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTPDT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PDTP_INQUIRE = "BP-P-INQ-PRD-TYPE   ";
    String CPN_PDTP_BROWER = "BP-R-BRW-PRD-TYPE   ";
    String CPN_PDME_BROWER = "BP-T-MAINT-PRDT-MENU";
    String CPN_PDTS_INQUIRE = "BP-P-INQ-PRDT-SUB   ";
    BPZSTPDT_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSTPDT_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
    BPZSTPDT_WS_PRDT_DATA WS_PRDT_DATA = new BPZSTPDT_WS_PRDT_DATA();
    BPZSTPDT_WS_MENU_DATA WS_MENU_DATA = new BPZSTPDT_WS_MENU_DATA();
    char WS_TBL_CCY_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPRPDME BPRPDME = new BPRPDME();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPTPDT BPCPTPDT = new BPCPTPDT();
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    BPCTPDME BPCTPDME = new BPCTPDME();
    SCCGWA SCCGWA;
    BPCSTPDT BPCSTPDT;
    public void MP(SCCGWA SCCGWA, BPCSTPDT BPCSTPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTPDT = BPCSTPDT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B002_CHECK_INPUT();
        if (pgmRtn) return;
        B003_OUTPUT_PTR_REC();
        if (pgmRtn) return;
    }
    public void B001_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 250;
        SCCMPAG.SCR_ROW_CNT = 12;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
    }
    public void B002_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSTPDT.PRDT_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            BPCPQPDT.PRDT_TYPE = BPCSTPDT.PRDT_TYPE;
            S000_CALL_BPZPQPDT();
            if (pgmRtn) return;
            if (BPCPQPDT.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCPQPDT.BOTTOM_IND == 'B') {
            WS_FLG = 'Y';
        } else {
            WS_FLG = 'N';
        }
    }
    public void B003_OUTPUT_PTR_REC() throws IOException,SQLException,Exception {
        if (WS_FLG == 'N') {
            B003_OUTPUT_PRDT_TYPE();
            if (pgmRtn) return;
        } else {
            B004_OUTPUT_PRD_MENU();
            if (pgmRtn) return;
        }
    }
    public void B003_OUTPUT_PRDT_TYPE() throws IOException,SQLException,Exception {
        B003_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCRBPDT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B003_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCRBPDT.RETURN_INFO == 'F') {
                IBS.init(SCCGWA, BPCPTPDT);
                BPCPTPDT.PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
                S000_CALL_BPZPTPDT();
                if (pgmRtn) return;
                if (BPCPTPDT.SUB_IND == 'Y') {
                    WS_PRDT_DATA.WS_IQOT_FLG = '+';
                } else if (BPCPTPDT.SUB_IND == 'N') {
                    WS_PRDT_DATA.WS_IQOT_FLG = '-';
                } else {
                    pgmRtn = true;
                    return;
                }
                R000_OUT_RECORD_TYPE();
                if (pgmRtn) return;
            }
        }
        B003_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B004_OUTPUT_PRD_MENU() throws IOException,SQLException,Exception {
        B004_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCTPDME.RETURN_INFO != 'N') {
            B004_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'F') {
                R000_OUT_RECORD_MENU();
                if (pgmRtn) return;
            }
        }
        B004_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B003_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRBPDT);
        BPCRBPDT.INFO.FUNC = 'S';
        BPCRBPDT.REQID = 2;
        BPRPDTP.KEY.PRDT_TYPE = BPCSTPDT.PRDT_TYPE;
        BPCRBPDT.INFO.POINTER = BPRPDTP;
        BPCRBPDT.INFO.LEN = 237;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B003_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'R';
        BPCRBPDT.REQID = 2;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B003_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'E';
        BPCRBPDT.REQID = 2;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B004_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCTPDME.INFO.INDEX_FLG = '2';
        BPRPDME.BUSI_TYPE = BPCSTPDT.PRDT_TYPE;
        BPCTPDME.INFO.POINTER = BPRPDME;
        BPCTPDME.LEN = 516;
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B004_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'N';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void B004_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'E';
        S000_CALL_BPZTPDME();
        if (pgmRtn) return;
    }
    public void R000_OUT_RECORD_TYPE() throws IOException,SQLException,Exception {
        WS_PRDT_DATA.WS_PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
        WS_PRDT_DATA.WS_LEVEL = BPRPDTP.BOTTOM_IND;
        WS_PRDT_DATA.WS_DESC = BPRPDTP.DESC;
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_PRDT_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 143;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void R000_OUT_RECORD_MENU() throws IOException,SQLException,Exception {
        WS_MENU_DATA.WS_PRDT_CODE = BPRPDME.KEY.PRDT_CODE;
        WS_MENU_DATA.WS_NAME = BPRPDME.PRDT_NAME;
        if (SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EXP_DATE) {
            WS_MENU_DATA.WS_STUS = '2';
        } else {
            if (SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.STOP_SOLD_DATE) {
                WS_MENU_DATA.WS_STUS = '1';
            } else {
                if (SCCGWA.COMM_AREA.AC_DATE >= BPRPDME.EFF_DATE) {
                    WS_MENU_DATA.WS_STUS = '0';
                } else {
                    WS_MENU_DATA.WS_STUS = '2';
                }
            }
        }
        WS_MENU_DATA.WS_BUSI_TYPE = BPRPDME.BUSI_TYPE;
        WS_MENU_DATA.WS_PRDT_MODEL = BPRPDME.PRDT_MODEL;
        WS_MENU_DATA.WS_PRDT_IND = BPRPDME.PRDT_IND;
        WS_MENU_DATA.WS_PARM_CODE = BPRPDME.PARM_CODE;
        WS_MENU_DATA.WS_EFF_DATE = BPRPDME.EFF_DATE;
        WS_MENU_DATA.WS_EXP_DATE = BPRPDME.EXP_DATE;
        WS_MENU_DATA.WS_STOP_SOLD_DATE = BPRPDME.STOP_SOLD_DATE;
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_MENU_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 173;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TS_REC);
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_INQUIRE, BPCPQPDT);
    }
    public void S000_CALL_BPZPTPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTS_INQUIRE, BPCPTPDT);
    }
    public void S000_CALL_BPZRBPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_BROWER, BPCRBPDT);
    }
    public void S000_CALL_BPZTPDME() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDME_BROWER, BPCTPDME);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
