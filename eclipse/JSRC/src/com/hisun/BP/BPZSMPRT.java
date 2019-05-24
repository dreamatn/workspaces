package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMPRT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String WS_ERR_MSG = " ";
    BPZSMPRT_WS_PRTR_INFO WS_PRTR_INFO = new BPZSMPRT_WS_PRTR_INFO();
    BPZSMPRT_WS_COND_FLG WS_COND_FLG = new BPZSMPRT_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRTR BPRPRTR = new BPRPRTR();
    BPCRMPRT BPCRMPRT = new BPCRMPRT();
    SCCGWA SCCGWA;
    BPCSMPRT BPCSMPRT;
    public void MP(SCCGWA SCCGWA, BPCSMPRT BPCSMPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMPRT = BPCSMPRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMPRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMPRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSMPRT.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPRT.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPRT.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPRT.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMPRT.I_FUNC == 'B') {
            B060_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMPRT.I_FUNC != 'B') {
            B070_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMPRT.I_FUNC != 'B' 
            && BPCSMPRT.PRTR_NAME.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        BPCRMPRT.INFO.FUNC = 'I';
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        BPCRMPRT.LEN = 440;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRTR);
        if (BPCRMPRT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRT_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        BPCRMPRT.INFO.FUNC = 'A';
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRMPRT.LEN = 440;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS1);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS2);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS3);
    }
    public void B031_CHECK_INPUT_AGAIN() throws IOException,SQLException,Exception {
        if (BPCSMPRT.PRTR_ADDR1.trim().length() == 0) {
            CEP.TRC(SCCGWA, "PRINTER ADDRESS1 MUST INPUT");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEFT_PRDT_MUST_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B031_CHECK_INPUT_AGAIN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        BPCRMPRT.INFO.FUNC = 'R';
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        BPCRMPRT.LEN = 440;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRMPRT.INFO.FUNC = 'U';
        BPCRMPRT.LEN = 440;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS1);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS2);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS3);
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        BPCRMPRT.INFO.FUNC = 'R';
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        BPCRMPRT.LEN = 440;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS1);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS2);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS3);
        BPCRMPRT.INFO.FUNC = 'D';
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        BPCRMPRT.LEN = 440;
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 440;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPRTR);
        IBS.init(SCCGWA, BPCRMPRT);
        BPCRMPRT.INFO.FUNC = 'B';
        BPCRMPRT.INFO.OPT = 'S';
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        BPCRMPRT.INFO.POINTER = BPRPRTR;
        BPCRMPRT.LEN = 440;
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        BPCRMPRT.INFO.OPT = 'N';
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRMPRT.RETURN_INFO);
        while (BPCRMPRT.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_PRTR_INFO.WS_PRTR_NAME = BPRPRTR.KEY.NAME;
            WS_PRTR_INFO.WS_PRTR_ADDRESS1 = BPRPRTR.ADDRESS1;
            WS_PRTR_INFO.WS_PRTR_ADDRESS2 = BPRPRTR.ADDRESS2;
            WS_PRTR_INFO.WS_PRTR_ADDRESS3 = BPRPRTR.ADDRESS3;
            B600_01_OUT_PRTR_INFO();
            if (pgmRtn) return;
            BPCRMPRT.INFO.OPT = 'N';
            S000_CALL_BPZRMPRT();
            if (pgmRtn) return;
        }
        BPCRMPRT.INFO.OPT = 'E';
        S000_CALL_BPZRMPRT();
        if (pgmRtn) return;
    }
    public void B070_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_PRTR_INFO);
        WS_PRTR_INFO.WS_PRTR_NAME = BPRPRTR.KEY.NAME;
        WS_PRTR_INFO.WS_PRTR_ADDRESS1 = BPRPRTR.ADDRESS1;
        WS_PRTR_INFO.WS_PRTR_ADDRESS2 = BPRPRTR.ADDRESS2;
        WS_PRTR_INFO.WS_PRTR_ADDRESS3 = BPRPRTR.ADDRESS3;
        SCCFMT.FMTID = "BP562";
        SCCFMT.DATA_PTR = WS_PRTR_INFO;
        SCCFMT.DATA_LEN = 440;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS1);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS2);
        CEP.TRC(SCCGWA, BPRPRTR.ADDRESS3);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_NAME);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS1);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS2);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS3);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRPRTR.KEY.NAME = BPCSMPRT.PRTR_NAME;
        BPRPRTR.ADDRESS1 = BPCSMPRT.PRTR_ADDR1;
        BPRPRTR.ADDRESS2 = BPCSMPRT.PRTR_ADDR2;
        BPRPRTR.ADDRESS3 = BPCSMPRT.PRTR_ADDR3;
    }
    public void B600_01_OUT_PRTR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_PRTR_INFO);
        SCCMPAG.DATA_LEN = 440;
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_NAME);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS1);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS2);
        CEP.TRC(SCCGWA, WS_PRTR_INFO.WS_PRTR_ADDRESS3);
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMPRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-PRTR-MAINT", BPCRMPRT);
        if (BPCRMPRT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMPRT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
