package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT6900 {
    DBParm TDTGRGP_RD;
    DBParm TDTGPMP_RD;
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT1 = "TD690";
    String WS_MSGID = " ";
    TDOT6900_WS_OUTPUT_DATA WS_OUTPUT_DATA = new TDOT6900_WS_OUTPUT_DATA();
    char WS_ACCU_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCMPAG SCCMPAG = new SCCMPAG();
    TDCTZZC TDCTZZC = new TDCTZZC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    TDCSFRZ TDCSFRZ = new TDCSFRZ();
    TDRGPMP TDRGPMP = new TDRGPMP();
    TDRGRGP TDRGRGP = new TDRGRGP();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    TDB6900_AWA_6900 TDB6900_AWA_6900;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDOT6900 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB6900_AWA_6900>");
        TDB6900_AWA_6900 = (TDB6900_AWA_6900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGRGP);
        IBS.init(SCCGWA, TDRGPMP);
        TDRGRGP.KEY.JRNNO = TDB6900_AWA_6900.JRNNO;
        TDRGRGP.KEY.TRD_DT = TDB6900_AWA_6900.TRD_DT;
        T000_INQUIRE_TDTGRGP();
        if (pgmRtn) return;
        TDRGPMP.KEY.PROVD_NO = TDRGRGP.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDRGRGP.PRD_NO;
        T000_READ_TDTGPMP();
        if (pgmRtn) return;
        B040_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_OUTPUT_DATA.WS_SIN_BAL = TDRGPMP.AMT;
        WS_OUTPUT_DATA.WS_SIN_PRIC = TDRGPMP.PRICE;
        WS_OUTPUT_DATA.WS_TOTAL = TDRGRGP.TOTAL;
        WS_OUTPUT_DATA.WS_ALL_BAL = WS_OUTPUT_DATA.WS_TOTAL * WS_OUTPUT_DATA.WS_SIN_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD690";
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 53;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_INQUIRE_TDTGRGP() throws IOException,SQLException,Exception {
        TDTGRGP_RD = new DBParm();
        TDTGRGP_RD.TableName = "TDTGRGP";
        IBS.READ(SCCGWA, TDRGRGP, TDTGRGP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.READ(SCCGWA, TDRGPMP, TDTGPMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
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
