package com.hisun.FX;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class FXZILLMT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "02H71";
    String K_OUTPUT_FMT1 = "CIX01";
    String WS_ERR_MSG = " ";
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    FXCRLLMT FXCRLLMT = new FXCRLLMT();
    FXRLLMT FXRLLMT = new FXRLLMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    FXCILLMT FXCILLMT;
    public void MP(SCCGWA SCCGWA, FXCILLMT FXCILLMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FXCILLMT = FXCILLMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FXZILLMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_PROC();
        if (pgmRtn) return;
        B002_QUERY_FXTLLMT_PROC();
        if (pgmRtn) return;
    }
    public void B001_CHECK_PROC() throws IOException,SQLException,Exception {
        if (FXCILLMT.KEY.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_MUST_INPUT_AMT);
        }
    }
    public void B002_QUERY_FXTLLMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FXCRLLMT);
        IBS.init(SCCGWA, FXRLLMT);
        CEP.TRC(SCCGWA, FXCILLMT.KEY.CCY);
        CEP.TRC(SCCGWA, FXCILLMT.KEY.TRCHNL);
        FXRLLMT.KEY.CCY = FXCILLMT.KEY.CCY;
        FXRLLMT.KEY.TRCHNL = FXCILLMT.KEY.TRCHNL;
        FXCRLLMT.FUNC = 'I';
        FXCRLLMT.REC_PTR = FXRLLMT;
        FXCRLLMT.REC_LEN = 98;
        S000_CALL_FXZRLLMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, FXRLLMT.BLIMIT_AMT);
        CEP.TRC(SCCGWA, FXRLLMT.SLIMIT_AMT);
        CEP.TRC(SCCGWA, FXCRLLMT.RETURN_INFO);
        if (FXCRLLMT.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "LNWXX");
            FXCILLMT.RETURN_INFO = 'F';
            FXCILLMT.BAMT = FXRLLMT.BLIMIT_AMT;
            FXCILLMT.SAMT = FXRLLMT.SLIMIT_AMT;
        } else if (FXCRLLMT.RETURN_INFO == 'N') {
            FXCILLMT.RETURN_INFO = 'N';
            FXCILLMT.BAMT = 0;
        } else {
            FXCILLMT.RETURN_INFO = 'O';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_FXZRLLMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "FX-R-MAIN-LLMT", FXCRLLMT);
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
