package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZISTAC {
    brParm GDTSTAC_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm DDTMST_RD;
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    char WS_STAC_FLG = ' ';
    char WS_DDTMST_FLG = ' ';
    char WS_TDTSMST_FLG = ' ';
    char WS_TDTCMST_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    TDRSMST TDRSMST = new TDRSMST();
    DDRMST DDRMST = new DDRMST();
    CICACCU CICACCU = new CICACCU();
    GDRSTAC GDRSTAC = new GDRSTAC();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    CICQACAC CICQACAC = new CICQACAC();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    GDCISTAC GDCISTAC;
    public void MP(SCCGWA SCCGWA, GDCISTAC GDCISTAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCISTAC = GDCISTAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDZISTAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCISTAC.FOUND_FLG);
        CEP.TRC(SCCGWA, GDCISTAC.AC);
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.ST_AC = GDCISTAC.AC;
        GDCISTAC.FOUND_FLG = 'N';
        T000_START_BY_DDAC_PROC();
        T000_READNEXT_PROC();
        while (WS_STAC_FLG != 'N' 
            && GDCISTAC.FOUND_FLG != 'Y') {
            CEP.TRC(SCCGWA, GDRSTAC.KEY.AC);
            CEP.TRC(SCCGWA, GDRSTAC.KEY.AC_SEQ);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRSTAC.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRSTAC.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDRCMST);
                TDRCMST.KEY.AC_NO = GDRSTAC.KEY.AC;
                T000_READ_TDTCMST();
                if (TDRCMST.STS != '1' 
                    && WS_TDTCMST_FLG == 'Y') {
                    GDCISTAC.FOUND_FLG = 'Y';
                }
            } else {
                if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                    IBS.init(SCCGWA, DDRMST);
                    DDRMST.KEY.CUS_AC = GDRSTAC.KEY.AC;
                    T000_READ_DDTMST();
                    if (DDRMST.AC_STS != 'C' 
                        && WS_DDTMST_FLG == 'Y') {
                        GDCISTAC.FOUND_FLG = 'Y';
                    }
                }
            }
            CEP.TRC(SCCGWA, WS_STAC_FLG);
            T000_READNEXT_PROC();
        }
        T000_END_BROWSE_PROC();
        CEP.TRC(SCCGWA, "WS-STAC-FLG  :");
        CEP.TRC(SCCGWA, WS_STAC_FLG);
    }
    public void T000_START_BY_DDAC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "ST_AC = :GDRSTAC.ST_AC";
        GDTSTAC_BR.rp.order = "CRT_DATE DESC";
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STAC_FLG = 'N';
        } else {
            WS_STAC_FLG = 'Y';
        }
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STAC_FLG = 'N';
        } else {
            WS_STAC_FLG = 'Y';
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTSTAC_BR);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTSMST_FLG = 'N';
        } else {
            WS_TDTSMST_FLG = 'Y';
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTCMST_FLG = 'N';
        } else {
            WS_TDTCMST_FLG = 'Y';
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTMST_FLG = 'N';
        } else {
            WS_DDTMST_FLG = 'Y';
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
