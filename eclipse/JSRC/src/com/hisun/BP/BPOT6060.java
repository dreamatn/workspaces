package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6060 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String CPN_PDTP_BROWER = "BP-R-BRW-PRD-TYPE   ";
    String CPN_PDME_BROWER = "BP-T-MAINT-PRDT-MENU";
    String CPN_PDTS_INQUIRE = "BP-P-INQ-PRDT-SUB   ";
    BPOT6060_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6060_WS_TEMP_VARIABLE();
    BPOT6060_WS_PRDT_DATA WS_PRDT_DATA = new BPOT6060_WS_PRDT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPDTP BPRPDTP = new BPRPDTP();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCPTPDT BPCPTPDT = new BPCPTPDT();
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    SCCGWA SCCGWA;
    BPB6060_AWA_6060 BPB6060_AWA_6060;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT6060 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6060_AWA_6060>");
        BPB6060_AWA_6060 = (BPB6060_AWA_6060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B200_TREE_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.PRDT_TYP.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.PRDT_TYP_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_TREE_PROCESS() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.PRDT_TYP.trim().length() > 0) {
            B210_OUT_PRDT_TYPE();
            if (pgmRtn) return;
        } else {
            B220_OUT_TOP_PRDT_TYPE();
            if (pgmRtn) return;
        }
    }
    public void B210_OUT_PRDT_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPDT);
        BPCPQPDT.PRDT_TYPE = BPB6060_AWA_6060.PRDT_TYP;
        S000_CALL_BPZPQPDT();
        if (pgmRtn) return;
        if (BPCPQPDT.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_PRD_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B201_CHECK_PRDT_SUB();
        if (pgmRtn) return;
        B202_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        R00_OUT_RECORD_1();
        if (pgmRtn) return;
    }
    public void B201_CHECK_PRDT_SUB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPTPDT);
        BPCPTPDT.PRDT_TYPE = BPCPQPDT.PRDT_TYPE;
        S000_CALL_BPZPTPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPTPDT.SUB_IND);
    }
    public void B202_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 190;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 1000;
        B_MPAG();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_TS_REC = " ";
    }
    public void B220_OUT_TOP_PRDT_TYPE() throws IOException,SQLException,Exception {
        B202_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        B210_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCRBPDT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B210_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCRBPDT.RETURN_INFO == 'F') {
                IBS.init(SCCGWA, BPCPTPDT);
                BPCPTPDT.PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
                S000_CALL_BPZPTPDT();
                if (pgmRtn) return;
                if (BPRPDTP.SUPR_TYPE.trim().length() == 0) {
                    BPRPDTP.SUPR_TYPE = BPRPDTP.KEY.PRDT_TYPE;
                }
                R00_OUT_RECORD_2();
                if (pgmRtn) return;
            }
        }
        B210_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B210_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRBPDT);
        BPCRBPDT.INFO.FUNC = 'S';
        BPCRBPDT.REQID = 1;
        BPCRBPDT.INFO.POINTER = BPRPDTP;
        BPCRBPDT.INFO.LEN = 237;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B210_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'R';
        BPCRBPDT.REQID = 1;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B210_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'E';
        BPCRBPDT.REQID = 1;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void R00_OUT_RECORD_1() throws IOException,SQLException,Exception {
        WS_PRDT_DATA.WS_PRDT_TYPE = BPCPQPDT.PRDT_TYPE;
        WS_PRDT_DATA.WS_LEVEL = BPCPQPDT.BOTTOM_IND;
        WS_PRDT_DATA.WS_DESC = BPCPQPDT.DESC;
        WS_PRDT_DATA.WS_UP_PRDT_TYPE = BPCPQPDT.SUPR_TYPE;
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_PRDT_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 128;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void R00_OUT_RECORD_2() throws IOException,SQLException,Exception {
        WS_PRDT_DATA.WS_PRDT_TYPE = BPRPDTP.KEY.PRDT_TYPE;
        WS_PRDT_DATA.WS_LEVEL = BPRPDTP.BOTTOM_IND;
        WS_PRDT_DATA.WS_DESC = BPRPDTP.DESC;
        WS_PRDT_DATA.WS_UP_PRDT_TYPE = BPRPDTP.SUPR_TYPE;
        WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_PRDT_DATA);
        WS_TEMP_VARIABLE.WS_LEN = 128;
        CEP.TRC(SCCGWA, WS_PRDT_DATA.WS_DESC);
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRDT_DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = (short) WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
    }
    public void S000_CALL_BPZPTPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTS_INQUIRE, BPCPTPDT);
    }
    public void S000_CALL_BPZRBPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_BROWER, BPCRBPDT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
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
