package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.TD.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1800 {
    brParm GDTSTAC_BR = new brParm();
    DBParm GDTSTAC_RD;
    String CPN_TD_ZM_ACC_PROC = "TD-ZM-ACC-PROC";
    String WS_ERR_MSG = " ";
    char WS_STAC_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCBINF SCCBINF = new SCCBINF();
    SCCFMT SCCFMT = new SCCFMT();
    TDCMACC TDCMACC = new TDCMACC();
    GDRSTAC GDRSTAC = new GDRSTAC();
    SCCGWA SCCGWA;
    GDB1800_AWA_1800 GDB1800_AWA_1800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1800_AWA_1800>");
        GDB1800_AWA_1800 = (GDB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CLOSE_TDAC_PROC();
        B030_DEL_STAC_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1800_AWA_1800.TXDD_AC);
        CEP.TRC(SCCGWA, GDB1800_AWA_1800.TXID_TYP);
        CEP.TRC(SCCGWA, GDB1800_AWA_1800.TXID_NO);
        if (GDB1800_AWA_1800.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CLOSE_TDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMACC);
        TDCMACC.AC_NO = GDB1800_AWA_1800.TXDD_AC;
        TDCMACC.ID_TYPE = GDB1800_AWA_1800.TXID_TYP;
        TDCMACC.ID_NO = GDB1800_AWA_1800.TXID_NO;
        TDCMACC.DRAW_MTH = '4';
        TDCMACC.BV_TYPE = '0';
        S000_CALL_TDZMACC();
    }
    public void B030_DEL_STAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDB1800_AWA_1800.TXDD_AC;
        T000_STARTBR_GDTSTAC_UPD();
        T000_READNEXT_GDTSTAC();
        while (WS_STAC_FLG != 'N') {
            GDRSTAC.RELAT_STS = 'R';
            GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_REC_PROC();
            T000_READNEXT_GDTSTAC();
        }
        T000_ENDBR_GDTSTAC();
    }
    public void S000_CALL_TDZMACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_TD_ZM_ACC_PROC, TDCMACC);
    }
    public void T000_STARTBR_GDTSTAC_UPD() throws IOException,SQLException,Exception {
        GDTSTAC_BR.rp = new DBParm();
        GDTSTAC_BR.rp.TableName = "GDTSTAC";
        GDTSTAC_BR.rp.where = "AC = :GDRSTAC.KEY.AC "
            + "AND RELAT_STS = 'N'";
        GDTSTAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
    }
    public void T000_READNEXT_GDTSTAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRSTAC, this, GDTSTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STAC_FLG = 'Y';
        } else {
            WS_STAC_FLG = 'N';
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_ENDBR_GDTSTAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTSTAC_BR);
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
