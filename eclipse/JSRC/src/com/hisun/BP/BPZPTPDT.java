package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPTPDT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PDTP_INQUIRE = "BP-P-INQ-PRD-TYPE   ";
    String CPN_PDTP_BROWER = "BP-R-BRW-PRD-TYPE   ";
    String CPN_PDME_BROWER = "BP-T-MAINT-PRDT-MENU";
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
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
    BPCRBPDT BPCRBPDT = new BPCRBPDT();
    BPCTPDME BPCTPDME = new BPCTPDME();
    SCCGWA SCCGWA;
    BPCPTPDT BPCPTPDT;
    public void MP(SCCGWA SCCGWA, BPCPTPDT BPCPTPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPTPDT = BPCPTPDT;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPTPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B002_CHECK_PRDT_SUB();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPTPDT.PRDT_TYPE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            BPCPQPDT.PRDT_TYPE = BPCPTPDT.PRDT_TYPE;
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
    public void B002_CHECK_PRDT_SUB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPDT);
        CEP.TRC(SCCGWA, WS_FLG);
        if (WS_FLG == 'N') {
            B003_CHECK_PRDT_SUB_TYPE();
            if (pgmRtn) return;
        } else {
            B004_CHECK_PRDT_SUB_MENU();
            if (pgmRtn) return;
        }
    }
    public void B003_CHECK_PRDT_SUB_TYPE() throws IOException,SQLException,Exception {
        B003_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCRBPDT.RETURN_INFO != 'N' 
            && BPCPTPDT.SUB_IND != 'Y') {
            B003_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCRBPDT.RETURN_INFO == 'F') {
                BPCPTPDT.SUB_IND = 'Y';
            }
        }
        if (BPCRBPDT.RETURN_INFO == 'N') {
            BPCPTPDT.SUB_IND = 'N';
        }
        B003_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B003_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDTP);
        IBS.init(SCCGWA, BPCRBPDT);
        BPCRBPDT.INFO.FUNC = 'S';
        BPCRBPDT.REQID = 3;
        BPRPDTP.KEY.PRDT_TYPE = BPCPTPDT.PRDT_TYPE;
        BPCRBPDT.INFO.POINTER = BPRPDTP;
        BPCRBPDT.INFO.LEN = 237;
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B003_02_READ_NEXT() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'R';
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B003_03_END_BROWSE() throws IOException,SQLException,Exception {
        BPCRBPDT.INFO.FUNC = 'E';
        S000_CALL_BPZRBPDT();
        if (pgmRtn) return;
    }
    public void B004_CHECK_PRDT_SUB_MENU() throws IOException,SQLException,Exception {
        B004_01_START_BROWSE();
        if (pgmRtn) return;
        while (BPCTPDME.RETURN_INFO != 'N' 
            && BPCPTPDT.SUB_IND != 'Y') {
            B004_02_READ_NEXT();
            if (pgmRtn) return;
            if (BPCTPDME.RETURN_INFO == 'F') {
                BPCPTPDT.SUB_IND = 'Y';
            }
        }
        if (BPCTPDME.RETURN_INFO == 'N') {
            BPCPTPDT.SUB_IND = 'N';
        }
        CEP.TRC(SCCGWA, BPCPTPDT);
        CEP.TRC(SCCGWA, BPCPTPDT.SUB_IND);
        B004_03_END_BROWSE();
        if (pgmRtn) return;
    }
    public void B004_01_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPDME);
        IBS.init(SCCGWA, BPCTPDME);
        BPCTPDME.INFO.FUNC = 'B';
        BPCTPDME.INFO.OPT = 'S';
        BPCTPDME.INFO.INDEX_FLG = '2';
        BPRPDME.BUSI_TYPE = BPCPTPDT.PRDT_TYPE;
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
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PDTP_INQUIRE, BPCPQPDT);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPTPDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPTPDT = ");
            CEP.TRC(SCCGWA, BPCPTPDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
