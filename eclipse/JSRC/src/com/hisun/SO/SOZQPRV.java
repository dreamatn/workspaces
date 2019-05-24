package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SOZQPRV {
    DBParm SOTPRIV_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    String WS_MSGID = " ";
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SORPRIV SORPRIV = new SORPRIV();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP17 SOCOP17 = new SOCOP17();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIPRV SOCIPRV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        C00_OUTP_PROC();
        CEP.TRC(SCCGWA, "SOZQPRV return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIPRV = new SOCIPRV();
        IBS.init(SCCGWA, SOCIPRV);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIPRV);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORPRIV);
        SORPRIV.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SORPRIV.KEY.ID = SOCIPRV.ID;
        SOTPRIV_RD = new DBParm();
        SOTPRIV_RD.TableName = "SOTPRIV";
        SOTPRIV_RD.errhdl = true;
        IBS.READ(SCCGWA, SORPRIV, SOTPRIV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void C00_OUTP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP17);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP17.ACTION = SOCIPRV.ACTION;
        SOCOP17.ID = SORPRIV.KEY.ID;
        SOCOP17.GRP_ID = SORPRIV.GRP_ID;
        SOCOP17.IND = SORPRIV.IND;
        SOCOP17.READ_IND = SORPRIV.READ_IND;
        SOCOP17.BROW_IND = SORPRIV.BROW_IND;
        SOCOP17.WRITE_IND = SORPRIV.WRITE_IND;
        SOCOP17.UPD_IND = SORPRIV.UPD_IND;
        SOCOP17.DEL_IND = SORPRIV.DEL_IND;
        SOCOP17.CRE_USER = SORPRIV.CRE_USER;
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP17.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP17.TS;
        JIBS_NumStr = SORPRIV.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        SOCOP17.TS = Integer.parseInt(JIBS_NumStr);
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP17.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP17.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORPRIV.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        SOCOP17.TS = Integer.parseInt(JIBS_NumStr);
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + SOCOP17.TS;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + SOCOP17.TS;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORPRIV.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        SOCOP17.TS = Integer.parseInt(JIBS_NumStr);
        SCCFMT.FMTID = "SOP17";
        SCCFMT.DATA_PTR = SOCOP17;
        SCCFMT.DATA_LEN = 37;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
