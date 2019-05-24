package com.hisun.SO;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;

import java.io.IOException;
import java.sql.SQLException;

public class SOZSBAT {
    boolean pgmRtn = false;
    SOZSBAT_WS_VARIABLES WS_VARIABLES = new SOZSBAT_WS_VARIABLES();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SOCSMGJ SOCSMGJ = new SOCSMGJ();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWAT;
    SCCGSCA_SC_AREA SC_AREA;
    SCRCWA_WK_INPUT_AREA WK_INPUT_AREA;
    String WK_LINK_PARM = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZSBAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WK_LINK_PARM = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.BAT_AREA_PTR);
        WK_INPUT_AREA = new SCRCWA_WK_INPUT_AREA();
        IBS.init(SCCGWA, WK_INPUT_AREA);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, WK_INPUT_AREA);
        SCRCWAT = (SCRCWAT) SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_INPUT_AREA.TELLER_NO;
        SOCCPSW.PSW = WK_INPUT_AREA.PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        WK_LINK_PARM = " ";
        if (SCCGWA.COMM_AREA.TR_BANK.trim().length() == 0) WS_VARIABLES.WK_PARM.BANK = 0;
        else WS_VARIABLES.WK_PARM.BANK = Short.parseShort(SCCGWA.COMM_AREA.TR_BANK);
        WS_VARIABLES.WK_PARM.ACDATE = SCRCWAT.AC_DATE_AREA[1-1].AC_DATE;
        if (SCRCWAT.JRN_IN_USE == ' ') WS_VARIABLES.WK_PARM.J_IND = 0;
        else WS_VARIABLES.WK_PARM.J_IND = Short.parseShort(""+SCRCWAT.JRN_IN_USE);
        WK_LINK_PARM = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WK_PARM);
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.MSGID);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_SEND_MSG_TO_OP() throws IOException,SQLException,Exception {
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}