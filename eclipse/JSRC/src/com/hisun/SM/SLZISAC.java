package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SLZISAC {
    DBParm SLTAC_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SLRAC SLRAC = new SLRAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLCISAC SLCISAC;
    public void MP(SCCGWA SCCGWA, SLCISAC SLCISAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SLCISAC = SLCISAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLZISAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SLCISAC.ITM.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SLCMSG_ERROR_MSG.SL_ITM_MUST_INPUT, SLCISAC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            T000_READ_SLAC_BY_ITM();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SLAC_BY_ITM() throws IOException,SQLException,Exception {
        SLTAC_RD = new DBParm();
        SLTAC_RD.TableName = "SLTAC";
        SLTAC_RD.col = "ITM";
        SLTAC_RD.where = "ITM = :SLCISAC.ITM";
        SLTAC_RD.fst = true;
        IBS.READ(SCCGWA, SLRAC, this, SLTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SLCISAC.AC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SLCISAC.AC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SLTAC SEL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SLCISAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SLCISAC=");
            CEP.TRC(SCCGWA, SLCISAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
