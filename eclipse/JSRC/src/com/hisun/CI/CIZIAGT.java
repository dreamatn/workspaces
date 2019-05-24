package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZIAGT {
    brParm CITAGT_BR = new brParm();
    boolean pgmRtn = false;
    int WS_I = 0;
    String WS_ENTY_NO = " ";
    char WS_AGT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CIRAGT CIRAGT = new CIRAGT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICIAGT CICIAGT;
    public void MP(SCCGWA SCCGWA, CICIAGT CICIAGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICIAGT = CICIAGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZIAGT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRAGT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICIAGT.FUNC == 'I') {
            R000_INQ_BY_CUS_AGT_TYP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICIAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICIAGT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICIAGT.DATA.AGT_TYP);
        if (CICIAGT.DATA.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_INPUT_ERR, CICIAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CICIAGT.DATA.AGT_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AGT_TYP_MUST_INPUT, CICIAGT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_BY_CUS_AGT_TYP() throws IOException,SQLException,Exception {
        CIRAGT.AGT_TYP = CICIAGT.DATA.AGT_TYP;
        CIRAGT.CI_NO = CICIAGT.DATA.CI_NO;
        T000_STARTBR_CITAGT();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (WS_AGT_FLG != 'N' 
            && WS_I != 200) {
            if (!CIRAGT.KEY.ENTY_NO.equalsIgnoreCase(WS_ENTY_NO)) {
                WS_I += 1;
                CICIAGT.DATA.AREA.ENTY_INF[WS_I-1].ENTY_NO = CIRAGT.KEY.ENTY_NO;
                WS_ENTY_NO = CIRAGT.KEY.ENTY_NO;
                CEP.TRC(SCCGWA, CIRAGT.KEY.ENTY_NO);
            }
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_I);
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_CITAGT() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.where = "ENTY_TYP < > '0' "
            + "AND AGT_TYP = :CIRAGT.AGT_TYP "
            + "AND CI_NO = :CIRAGT.CI_NO";
        CITAGT_BR.rp.order = "ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AGT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AGT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICIAGT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICIAGT=");
            CEP.TRC(SCCGWA, CICIAGT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
