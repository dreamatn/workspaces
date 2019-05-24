package com.hisun.AI;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSIADP {
    brParm AITIADP_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    AIZSIADP_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSIADP_WS_BRW_OUTPUT();
    char SIADP_RETURN_INFO = ' ';
    String WS_ERR_MSG = " ";
    String TBL_AITIADP = "AITIADP";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRIADP AIRIADP = new AIRIADP();
    SCCMSG SCCMSG = new SCCMSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    AICSIADP AICSIADP;
    public void MP(SCCGWA SCCGWA, AICSIADP AICSIADP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSIADP = AICSIADP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSIADP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 20;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AIRIADP);
        AIRIADP.KEY.GL_BOOK_FLG = AICSIADP.GL_BOOK_FLG;
        AIRIADP.KEY.ITM = AICSIADP.ITM;
        T000_STARTBR_AITIADP();
        if (pgmRtn) return;
        T000_READNEXT_AITIADP();
        if (pgmRtn) return;
        if (SIADP_RETURN_INFO == 'N') {
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (SIADP_RETURN_INFO != 'N') {
            B021_TRANS_DATA_BRW_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_AITIADP();
            if (pgmRtn) return;
        }
        T000_ENDBR_AITIADP();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_AITIADP() throws IOException,SQLException,Exception {
        AITIADP_BR.rp = new DBParm();
        AITIADP_BR.rp.TableName = "AITIADP";
        AITIADP_BR.rp.where = "GL_BOOK_FLG = :AIRIADP.KEY.GL_BOOK_FLG "
            + "AND ITM = :AIRIADP.KEY.ITM";
        IBS.STARTBR(SCCGWA, AIRIADP, this, AITIADP_BR);
    }
    public void T000_READNEXT_AITIADP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRIADP, this, AITIADP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SIADP_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SIADP_RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_AITIADP;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_AITIADP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITIADP_BR);
    }
    public void B021_TRANS_DATA_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GL_BOOK_FLG = AIRIADP.KEY.GL_BOOK_FLG;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_ITM = AIRIADP.KEY.ITM;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_ITM_NAME = AIRIADP.ITM_NAME;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_ITM_STS = AIRIADP.ITM_STS;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_PRDT_CODE = AIRIADP.KEY.PRDT_CODE;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_PRDT_NAME = AIRIADP.PRDT_NAME;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_EFF_DATE = AIRIADP.EFF_DATE;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_EXP_DATE = AIRIADP.EXP_DATE;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_PRDT_STS = AIRIADP.PRDT_STS;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GLMST = AIRIADP.KEY.GLMST;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GLMST_NAME = AIRIADP.GLMST_NAME;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GLMST_EFF_DATE = AIRIADP.GLMST_EFF_DATE;
        WS_BRW_OUTPUT.WS_BRW_OUTPUT_GLMST_EXP_DATE = AIRIADP.GLMST_EXP_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 470;
        B_MPAG();
        if (pgmRtn) return;
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
