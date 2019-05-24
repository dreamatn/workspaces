package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUIQVA {
    DBParm DCTIAACR_RD;
    DBParm DCTACLNK_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_TBL_FLAG_IAACR = ' ';
    char WS_TBL_FLAG_ACLNK = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRACLNK DCRACLNK = new DCRACLNK();
    SCCGWA SCCGWA;
    DCCUIQVA DCCUIQVA;
    public void MP(SCCGWA SCCGWA, DCCUIQVA DCCUIQVA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUIQVA = DCCUIQVA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUIQVA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_MSG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUIQVA.INP_DATA.SUB_AC);
        if (DCCUIQVA.INP_DATA.SUB_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_MUST_INPUT, DCCUIQVA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_MSG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.SUB_AC = DCCUIQVA.INP_DATA.SUB_AC;
        T000_READ_DCTIAACR();
        if (pgmRtn) return;
        if (WS_TBL_FLAG_IAACR == 'Y') {
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.VIA_AC = DCRIAACR.KEY.VIA_AC;
            CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
            T000_READ_DCTACLNK();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "SUB_AC = :DCRIAACR.SUB_AC";
        DCTIAACR_RD.fst = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_TBL_FLAG_IAACR = 'N';
        } else {
            WS_TBL_FLAG_IAACR = 'Y';
            DCCUIQVA.OUTP_DATA.VIA_AC = DCRIAACR.KEY.VIA_AC;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.where = "VIA_AC = :DCRACLNK.VIA_AC";
        DCTACLNK_RD.fst = true;
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_TBL_FLAG_ACLNK = 'N';
        } else {
            WS_TBL_FLAG_ACLNK = 'Y';
            DCCUIQVA.OUTP_DATA.CARD_NO = DCRACLNK.KEY.CARD_NO;
        }
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
