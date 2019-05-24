package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SOZCPRIV {
    brParm SOTPRIV_BR = new brParm();
    DBParm SOTGRP_RD;
    boolean pgmRtn = false;
    short WK_REC_LEN = 0;
    int WK_RESP = 0;
    int WK_JRN_KEY = 0;
    short WK_I = 0;
    short WK_J = 0;
    char WK_FND = ' ';
    char WK_FLOW_IND = ' ';
    String WK_ABCD = " ";
    String WK_AB_PGM = " ";
    String WK_AB_PSW = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SORGRP SORGRP = new SORGRP();
    SORPRIV SORPRIV = new SORPRIV();
    SCCGWA SCCGWA;
    SOCCPRIV SOCCPRIV;
    public void MP(SCCGWA SCCGWA, SOCCPRIV SOCCPRIV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SOCCPRIV = SOCCPRIV;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_CHK_PROC();
        if (pgmRtn) return;
        C00_MAIN_PROC();
        if (pgmRtn) return;
        D00_OUTP_PROC();
        if (pgmRtn) return;
        E00_GO_BACK();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_CHK_PROC() throws IOException,SQLException,Exception {
    }
    public void C00_MAIN_PROC() throws IOException,SQLException,Exception {
        SORPRIV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORPRIV.KEY.ID = SOCCPRIV.TLR_ID;
        CEP.TRC(SCCGWA, SORPRIV.KEY.BANK_NO);
        CEP.TRC(SCCGWA, SORPRIV.KEY.ID);
        SOTPRIV_BR.rp = new DBParm();
        SOTPRIV_BR.rp.TableName = "SOTPRIV";
        IBS.STARTBR(SCCGWA, SORPRIV, SOTPRIV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, SOCMSG.SO_TLR_NO_PRIV, SOCCPRIV.RC);
            CEP.ERR(SCCGWA, SOCMSG.SO_TLR_NO_PRIV);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTPRIV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        WK_FND = 'N';
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SORPRIV.KEY.ID.equalsIgnoreCase(SOCCPRIV.TLR_ID) 
            && WK_FND != 'Y') {
            IBS.READNEXT(SCCGWA, SORPRIV, this, SOTPRIV_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, SORPRIV.KEY.ID);
            CEP.TRC(SCCGWA, SORPRIV.GRP_ID);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (SORPRIV.GRP_ID.equalsIgnoreCase("ALL")) {
                    WK_FND = 'Y';
                } else {
                    SORGRP.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
                    SORGRP.KEY.ID = SORPRIV.GRP_ID;
                    SORGRP.SERV_ID = SOCCPRIV.SERV_ID;
                    SOTGRP_RD = new DBParm();
                    SOTGRP_RD.TableName = "SOTGRP";
                    IBS.READ(SCCGWA, SORGRP, SOTGRP_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        WK_FND = 'Y';
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    } else {
                        IBS.init(SCCGWA, SCCEXCP);
                        SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTGRP";
                        SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                        SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
                        B_DB_EXCP();
                        if (pgmRtn) return;
                    }
                }
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTPRIV";
                SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
                SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
                B_DB_EXCP();
                if (pgmRtn) return;
            }
        }
        if (WK_FND == 'N') {
            IBS.CPY2CLS(SCCGWA, SOCMSG.SO_TLR_NO_PRIV, SOCCPRIV.RC);
            CEP.ERR(SCCGWA, SOCMSG.SO_TLR_NO_PRIV);
        }
    }
    public void D00_OUTP_PROC() throws IOException,SQLException,Exception {
    }
    public void E00_GO_BACK() throws IOException,SQLException,Exception {
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
