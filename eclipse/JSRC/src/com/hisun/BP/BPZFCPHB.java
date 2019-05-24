package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCPHB {
    DBParm BPTFSCH_RD;
    brParm BPTFCPH_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPZFCPHB_WS_LINE WS_LINE = new BPZFCPHB_WS_LINE();
    String WS_ERR_MSG = " ";
    BPRFCPH BPRFCPH = new BPRFCPH();
    BPCFCPHM BPCFCPHM = new BPCFCPHM();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCFCPHB BPCFCPHB;
    public void MP(SCCGWA SCCGWA, BPCFCPHB BPCFCPHB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCPHB = BPCFCPHB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCPHB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MPAG_START();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFSCH);
        BPRFSCH.KEY.CTRT_NO = BPCFCPHB.CTRT_NO;
        CEP.TRC(SCCGWA, BPCFCPHB.CTRT_NO);
        B200_ACCESS_BPRFSCH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, "GWA-DBIO-FOUND");
            WS_LINE.WS_LINE_CTRT_NO = BPRFSCH.KEY.CTRT_NO;
            WS_LINE.WS_LINE_REL_CTNO = BPRFSCH.REL_CTRT_NO;
            WS_LINE.WS_LINE_FEE_TYPE = BPRFSCH.FEE_TYPE;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_NO_NOTFOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRFSCH);
        BPRFCPH.KEY.CTRT_NO = BPCFCPHB.CTRT_NO;
        T000_STARTBR_BPTFCPH();
        if (pgmRtn) return;
        T000_READNEXT_BPTFCPH();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            WS_LINE.WS_LINE_AGGR_TYPE = BPRFCPH.AGGR_TYPE;
            WS_LINE.WS_LINE_REF_CCY = BPRFCPH.REF_CCY;
            WS_LINE.WS_LINE_REF_METH = BPRFCPH.REF_METHOD;
            WS_LINE.WS_LINE_VALUE_DATE = BPRFCPH.KEY.VALUE_DATE;
            WS_LINE.WS_LINE_INT_BAS = BPRFCPH.INT_BAS;
            WS_LINE.WS_LINE_MULTI = BPRFCPH.MULTI;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LINE);
            SCCMPAG.DATA_LEN = 88;
            B_MPAG();
            if (pgmRtn) return;
            T000_READNEXT_BPTFCPH();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFCPH();
        if (pgmRtn) return;
    }
    public void B100_MPAG_START() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B200_ACCESS_BPRFSCH() throws IOException,SQLException,Exception {
        T000_READ_BPTFSCH();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTFSCH() throws IOException,SQLException,Exception {
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.upd = true;
        IBS.READ(SCCGWA, BPRFSCH, BPTFSCH_RD);
    }
    public void T000_STARTBR_BPTFCPH() throws IOException,SQLException,Exception {
        BPTFCPH_BR.rp = new DBParm();
        BPTFCPH_BR.rp.TableName = "BPTFCPH";
        BPTFCPH_BR.rp.eqWhere = "CTRT_NO";
        BPTFCPH_BR.rp.order = "VALUE_DATE";
        IBS.STARTBR(SCCGWA, BPRFCPH, BPTFCPH_BR);
    }
    public void T000_READNEXT_BPTFCPH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFCPH, this, BPTFCPH_BR);
    }
    public void T000_ENDBR_BPTFCPH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFCPH_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
